package jobs4u.base.network.responseprocessors;

import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.network.SerializationUtil;
import jobs4u.base.network.data.DataBlock;
import jobs4u.base.network.data.DataDTO;

import java.util.ArrayList;
import java.util.List;

public class ApplicationListResponseProcessor implements ResponseProcessor<ApplicationDTO> {
    @Override
    public List<ApplicationDTO> process(DataDTO dataDTO) {
        List<ApplicationDTO> applicationDTOList = new ArrayList<>();
        List<DataBlock> dataBlocks = dataDTO.dataBlockList();

        for (DataBlock dataBlock : dataBlocks) {
            ApplicationDTO applicationDTO = (ApplicationDTO) SerializationUtil.deserialize(dataBlock.data());
            applicationDTOList.add(applicationDTO);
        }

        return applicationDTOList;
    }
}
