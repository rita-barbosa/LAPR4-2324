package jobs4u.base.network.responseprocessors;

import jobs4u.base.applicationmanagement.dto.ApplicationDTO;
import jobs4u.base.network.SerializationUtil;
import jobs4u.base.network.data.DataBlock;
import jobs4u.base.network.data.DataDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ApplicationListResponseProcessor implements ResponseProcessor<Map.Entry<ApplicationDTO, Integer>> {
    @Override
    public List<Map.Entry<ApplicationDTO, Integer>> process(DataDTO dataDTO) {
        List<Map.Entry<ApplicationDTO, Integer>> applicationDTOList = new ArrayList<>();
        List<DataBlock> dataBlocks = dataDTO.dataBlockList();

        for (DataBlock dataBlock : dataBlocks) {
            Map.Entry<ApplicationDTO, Integer> entry = (Map.Entry<ApplicationDTO, Integer>) SerializationUtil.deserialize(dataBlock.data());
//            ApplicationDTO applicationDTO = (ApplicationDTO) SerializationUtil.deserialize(dataBlock.data());
            applicationDTOList.add(entry);
        }

        return applicationDTOList;
    }
}
