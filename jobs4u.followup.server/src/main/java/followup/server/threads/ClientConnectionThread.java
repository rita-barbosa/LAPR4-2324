package followup.server.threads;

import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.network.FollowUpRequestCodes;
import jobs4u.base.network.SerializationUtil;
import jobs4u.base.network.data.DataBlock;
import jobs4u.base.network.data.DataDTO;
import jobs4u.base.usermanagement.domain.BasePasswordPolicy;
import jobs4u.base.usermanagement.domain.BaseRoles;


import java.io.*;
import java.net.Socket;
import java.util.List;

//TODO ADD LOGGER COMMENTS
public class ClientConnectionThread implements Runnable {

    private final Socket socket;
    private DataOutputStream sOut;
    private DataInputStream sIn;

    public ClientConnectionThread(Socket clientSock) {
        socket = clientSock;
        try {
            sOut = new DataOutputStream(socket.getOutputStream());
            sIn = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            //add logger comment
        }
    }

    @Override
    public void run() {
        Thread requestThread = null;
        DataDTO dataDTO;
        int code;

        try {
            dataDTO = receiveRequest();
        } catch (IOException e) {
            //add logger comment
            throw new RuntimeException(e + "\nCouldn't read first data request from input stream.");
        }

        if (!authenticationProtocol(dataDTO)) {
            sendEmptyResponse(FollowUpRequestCodes.ERR);
            closeConnection();
            return;
        } else {
            sendEmptyResponse(FollowUpRequestCodes.ACK);
        }

        do {
            try {
                dataDTO = receiveRequest();
                code = dataDTO.code();

                if (code == FollowUpRequestCodes.EMAIL.getCode()) {
                    requestThread = new Thread(new EmailSendingRequestThread(dataDTO, new DataOutputStream(socket.getOutputStream())));

                } else if (code == FollowUpRequestCodes.NOTIFLIST.getCode()) {
                    requestThread = new Thread(new NotificationRequestThread(dataDTO, new DataOutputStream(socket.getOutputStream())));

                } else if (code == FollowUpRequestCodes.APPLIST.getCode() || code == FollowUpRequestCodes.JOBOPLIST.getCode()) {
                    requestThread = new Thread(new ListingRequestThread(dataDTO, new DataOutputStream(socket.getOutputStream())));

                } else if (code == FollowUpRequestCodes.COMMTEST.getCode()) {
                    sendEmptyResponse(FollowUpRequestCodes.ACK);
                }else if (code == FollowUpRequestCodes.DISCONN.getCode()){
                    break;
                }
            } catch (IOException e) {
                //add logger comment
                throw new RuntimeException(e + "\nCouldn't create thread.\n");
            }

            if (code != FollowUpRequestCodes.DISCONN.getCode()) {
                assert requestThread != null;
                requestThread.start();

                try {
                    requestThread.join();
                } catch (InterruptedException e) {
                    //add logger comment
                    throw new RuntimeException(e + "\nThread join error.");
                }
            }

        } while (code != FollowUpRequestCodes.DISCONN.getCode());

        sendEmptyResponse(FollowUpRequestCodes.ACK);
        closeConnection();
    }

    private DataDTO receiveRequest() throws IOException {
        int dataLength = sIn.readInt();
        byte[] dataBytes = new byte[dataLength];
        sIn.readFully(dataBytes);
        return DataDTO.fromByteArray(dataBytes);
    }

    private void sendEmptyResponse(FollowUpRequestCodes code) {
        DataDTO dataDTO = new DataDTO(code.getCode());
        try {
            byte[] bytes = dataDTO.toByteArray();
            sOut.writeInt(bytes.length);
            sOut.write(bytes);
            sOut.flush();
        } catch (IOException e) {
            //add logger comment
        }
    }

    public synchronized boolean authenticationProtocol(DataDTO dataDto) {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new BasePasswordPolicy(), new PlainTextEncoder());
        AuthenticationService authenticationService = AuthzRegistry.authenticationService();
        List<DataBlock> dataBlocks = dataDto.dataBlockList();

        Username username = (Username) SerializationUtil.deserialize(dataBlocks.get(0).data());
        String password = (String) SerializationUtil.deserialize(dataBlocks.get(1).data());

        return authenticationService.authenticate(username.toString(), password, BaseRoles.CANDIDATE_USER, BaseRoles.CUSTOMER_USER,
                BaseRoles.CUSTOMER_MANAGER).isPresent();
    }

    public void closeConnection() {
        try {
            System.out.println("\nClosing connection.\n");
            socket.close();
        } catch (IOException e) {
            //add logger comment
            throw new RuntimeException(e + "\nCouldn't close socket.");
        }
    }

}