package followup.server.threads;

import jobs4u.base.network.SerializationUtil;
import jobs4u.base.network.data.DataDTO;
import jobs4u.base.notificationmanagement.NotificationDTO;
import jobs4u.base.notificationmanagement.NotificationManagementService;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

//TODO ADD LOGGER COMMENTS
public class NotificationRequestThread implements Runnable {

    public final DataDTO data;
    private final DataOutputStream sOut;

    public NotificationRequestThread(DataDTO dataDTO, DataOutputStream dataOutputStream) {
        this.data = dataDTO;
        this.sOut = dataOutputStream;
    }

    @Override
    public void run() {
        // get the notificationManagementService
        NotificationManagementService notificationManagementService = new NotificationManagementService();
        String username = (String) SerializationUtil.deserialize(data.dataBlockList().get(0).data());

        //get notificationDTO list
        //convert to dataDTO
        List<NotificationDTO> notificationsDTO = notificationManagementService.getNotificationsByUsername(username);
        DataDTO dataDTO = DataDTO.turnListIntoDataDTO(data.code(), notificationsDTO);

        //send response
        try {
            byte[] bytes = dataDTO.toByteArray();
            sOut.writeInt(bytes.length);
            sOut.write(bytes);
            sOut.flush();
            sOut.flush();
        } catch (IOException e) {
            //add logger comment
            throw new RuntimeException(e + "\nCouldn't serialize to stream and send response.\n");
        }
    }

}
