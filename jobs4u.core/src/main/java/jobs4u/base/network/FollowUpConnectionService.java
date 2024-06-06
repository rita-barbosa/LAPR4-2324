package jobs4u.base.network;

import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.network.responseprocessors.ApplicationListResponseProcessor;
import jobs4u.base.network.responseprocessors.JobOpeningListResponseProcessor;
import jobs4u.base.network.responseprocessors.NotificationListResponseProcessor;
import jobs4u.base.network.responseprocessors.ResponseProcessor;
import jobs4u.base.network.data.DataDTO;
import jobs4u.base.notificationmanagement.NotificationDTO;
import org.apache.commons.lang3.tuple.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


//TODO ADD LOGGER COMMENTS
//TODO CHECK SERVERIP ADDRESS
public class FollowUpConnectionService {

    //get server IP
    private static Socket clientSocket;
    private static DataOutputStream sOut;
    private static DataInputStream sIn;
    private InetAddress serverIp;
    public static int PORT_NUMBER = 6666;

    public FollowUpConnectionService() {
    }

    public Pair<Boolean, String> establishConnection(Username username, String password) {
        try {
            if (!defineServerIpAddress()) {
                return Pair.of(false, "Server IP Address not válid (" + serverIp + ").");
            }
            clientSocket = new Socket(serverIp, PORT_NUMBER);
            sOut = new DataOutputStream(clientSocket.getOutputStream());
            sIn = new DataInputStream(clientSocket.getInputStream());

        } catch (IOException ex) {
            return Pair.of(false, "Socket unable to connect to server.");
        }

        sendAuthenticationRequest(username, password);

        if (receiveEmptyResponse()) {
            return Pair.of(true, "Connection successfully established.");
        } else {
            return Pair.of(false, "Unable to establish connection, wrong credentials.");
        }
    }

    private boolean defineServerIpAddress() {
        try {
//            serverIp = InetAddress.getByName("10.8.0.80");
            serverIp = InetAddress.getLocalHost();
            return true;
        } catch (UnknownHostException ex) {
            //add logger comments
        }
        return false;
    }

    private static void sendAuthenticationRequest(Username username, String password) {
        try {
            DataDTO dataDTO = new DataDTO(FollowUpRequestCodes.AUTH.getCode());
            byte[] stringBytes = SerializationUtil.serialize(username);
            dataDTO.addDataBlock(stringBytes.length, stringBytes);
            stringBytes = SerializationUtil.serialize(password);
            dataDTO.addDataBlock(stringBytes.length, stringBytes);
            byte[] message = dataDTO.toByteArray();
            sOut.writeInt(message.length);
            sOut.write(message);
            sOut.flush();

        } catch (IOException e) {
            throw new RuntimeException(e + "\n Unable to send authentication request.\n");
        }
    }

    public static Pair<Boolean, String> closeConnection() {
        try {
            DataDTO dataDTO = new DataDTO(FollowUpRequestCodes.DISCONN.getCode());
            byte[] message = dataDTO.toByteArray();
            sOut.writeInt(message.length);
            sOut.write(message);
            sOut.flush();

            boolean response = receiveEmptyResponse();
            clientSocket.close();
            if (response){
                return Pair.of(true, "Connection successfully closed.\n");
            }else {
                throw new RuntimeException();
            }

        } catch (IOException e) {
            //put logger comment
            return Pair.of(false, "Connection closed.");
        }
    }

    public List<JobOpeningDTO> receiveJobOpeningsDataList(Username username) {
        //send job opening request with dataDTO
        try {
            DataDTO dataDTO = new DataDTO(FollowUpRequestCodes.JOBOPLIST.getCode());
            byte[] serialized = SerializationUtil.serialize(username);
            dataDTO.addDataBlock(serialized.length, serialized);
            byte[] message = dataDTO.toByteArray();
            sOut.writeInt(message.length);
            sOut.write(message);
            sOut.flush();
            return processListResponse(new JobOpeningListResponseProcessor());

        } catch (IOException e) {
            throw new RuntimeException(e + "\n Unable to send job opening list request.\n");
        }
    }

    public List<ApplicationDTO> receiveCandidateApplicationList(String username) {
        //send candidate applications request with dataDTO
        try {
            DataDTO dataDTO = new DataDTO(FollowUpRequestCodes.APPLIST.getCode());
            byte[] serialized = SerializationUtil.serialize(username);
            dataDTO.addDataBlock(serialized.length, serialized);
            byte[] message = dataDTO.toByteArray();
            sOut.writeInt(message.length);
            sOut.write(message);
            sOut.flush();
            return processListResponse(new ApplicationListResponseProcessor());

        } catch (IOException e) {
            throw new RuntimeException(e + "\n Unable to send applications list request.\n");
        }
    }

    public List<NotificationDTO> receiveNotificationList(String username) {
        //send client user notifications request with dataDTO
        try {
            DataDTO dataDTO = new DataDTO(FollowUpRequestCodes.NOTIFLIST.getCode());
            byte[] serialized = SerializationUtil.serialize(username);
            dataDTO.addDataBlock(serialized.length, serialized);
            byte[] message = dataDTO.toByteArray();
            sOut.writeInt(message.length);
            sOut.write(message);
            sOut.flush();
            return processListResponse(new NotificationListResponseProcessor());

        } catch (IOException e) {
            throw new RuntimeException(e + "\n Unable to send notification list request.\n");
        }
    }

    private <T> List<T> processListResponse(ResponseProcessor<T> processor) {
        try {
            int byteLength = sIn.readInt();
            byte[] bytes = new byte[byteLength];
            sIn.readFully(bytes);
            DataDTO dataDTO = DataDTO.fromByteArray(bytes);
            return processor.process(dataDTO);

        } catch (IOException e) {
            throw new RuntimeException(e + "\n Unable to process response.\n");
        }
    }

    public boolean sendEmail(String senderEmail, String receiverEmail, String topic, String info) {
        //send email request with dataDTO
        try {
            DataDTO dataDTO = new DataDTO(FollowUpRequestCodes.EMAIL.getCode());
            List<String> params = new ArrayList<>();
            params.add(senderEmail);
            params.add(receiverEmail);
            params.add(topic);
            params.add(info);

            for (String parameter : params){
                byte[] serialized = SerializationUtil.serialize(parameter);
                dataDTO.addDataBlock(serialized.length, serialized);
            }

            byte[] message = dataDTO.toByteArray();
            sOut.writeInt(message.length);
            sOut.write(message);
            sOut.flush();
            return receiveEmptyResponse();

        } catch (IOException e) {
            throw new RuntimeException(e + "\n Unable to send email request.\n");
        }
    }

    private static boolean receiveEmptyResponse() {
        DataDTO dataDTO = null;
        try {
            int byteLength = sIn.readInt();
            byte[] bytes = new byte[byteLength];
            sIn.readFully(bytes);
            dataDTO = DataDTO.fromByteArray(bytes);
        } catch (IOException e) {
            //get LOGGER
        }

        assert dataDTO != null;
        int code = dataDTO.code();

        if (code == FollowUpRequestCodes.ACK.getCode()) {
            return true;
        }

        assert code == FollowUpRequestCodes.ERR.getCode();
        closeConnection();
        return false;
    }

}