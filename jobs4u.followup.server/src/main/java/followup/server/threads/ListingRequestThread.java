package followup.server.threads;

import eapli.framework.infrastructure.authz.domain.model.Username;
import jobs4u.base.network.FollowUpRequestCodes;
import jobs4u.base.applicationmanagement.application.ApplicationManagementService;
import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.jobopeningmanagement.application.JobOpeningManagementService;
import jobs4u.base.jobopeningmanagement.dto.JobOpeningDTO;
import jobs4u.base.network.SerializationUtil;
import jobs4u.base.network.data.DataDTO;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

//TODO ADD LOGGER COMMENTS
public class ListingRequestThread implements Runnable {

    public final DataDTO data;
    private final DataOutputStream sOut;

    public ListingRequestThread(DataDTO dataDTO, DataOutputStream dataOutputStream) {
        this.data = dataDTO;
        this.sOut = dataOutputStream;
    }

    @Override
    public void run() {
        int code = data.code();
        Username username = (Username) SerializationUtil.deserialize(data.dataBlockList().get(0).data());
        DataDTO dataDTO = null;


        // check code
        //according to code redirection to the right service
        if (code == FollowUpRequestCodes.APPLIST.getCode()){
            //get Application Service
            ApplicationManagementService appSrv = new ApplicationManagementService();
            List<ApplicationDTO> applicationsDTO = appSrv.getApplicationsListByUsername(username);
            dataDTO = DataDTO.turnListIntoDataDTO(code, applicationsDTO);

        } else if (code == FollowUpRequestCodes.JOBOPLIST.getCode()) {
            //get Job Opening Service
            JobOpeningManagementService jobSrv = new JobOpeningManagementService();
            Iterable<JobOpeningDTO> jobOpeningsDTO = jobSrv.getJobOpeningFromUsername(username);
            dataDTO = DataDTO.turnListIntoDataDTO(code, jobOpeningsDTO);
        }

        //send response
        assert dataDTO != null;
        try {
            byte[] bytes = dataDTO.toByteArray();
            sOut.writeInt(bytes.length);
            sOut.write(bytes);
            sOut.flush();
        } catch (IOException e) {
            //add logger comment
            throw new RuntimeException(e + "\nCouldn't serialize to stream and send response.\n");
        }
    }

}