package jobs4u.base.recruitmentprocessmanagement.application;

import eapli.framework.time.domain.model.DateInterval;
import jobs4u.base.infrastructure.persistence.PersistenceContext;
import jobs4u.base.jobopeningmanagement.domain.JobOpening;
import jobs4u.base.recruitmentprocessmanagement.domain.Phase;
import jobs4u.base.recruitmentprocessmanagement.domain.RecruitmentProcess;
import jobs4u.base.recruitmentprocessmanagement.dto.AllPhasesDTO;
import jobs4u.base.recruitmentprocessmanagement.dto.PhaseDTO;
import jobs4u.base.recruitmentprocessmanagement.repository.PhaseRepository;
import jobs4u.base.recruitmentprocessmanagement.repository.RecruitmentProcessRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecruitmentProcessManagementService {

    private static final RecruitmentProcessRepository recruitmentProcessRepository = PersistenceContext.repositories().recruitmentProcesses();

    private static final PhaseRepository phaseRepository = PersistenceContext.repositories().phases();

    public static RecruitmentProcess setupRecruitmentProcess(Calendar start, Calendar end, AllPhasesDTO allPhasesDTO, JobOpening jobOpening){

        List<Phase> listPhases = new ArrayList<>();

        RecruitmentProcess recruitmentProcess = new RecruitmentProcess(start, end, listPhases);

        for (PhaseDTO phaseDTO : allPhasesDTO.getListOfPhases()){
            Phase phase = new Phase(phaseDTO.getPhaseType(), phaseDTO.getDescription(), phaseDTO.getStatus(), phaseDTO.getInitialDate(), phaseDTO.getFinalDate());
            phase = phaseRepository.save(phase);
            listPhases.add(phase);
        }

        recruitmentProcess.setPhases(listPhases);

        recruitmentProcess = recruitmentProcessRepository.save(recruitmentProcess);

        for (Phase phase : listPhases){
            phase.setRecruitmentprocess(recruitmentProcess);
            phaseRepository.save(phase);
        }

        return recruitmentProcess;
    }

    public boolean saveToRepository(RecruitmentProcess recruitmentProcess){
        List<Phase> list = recruitmentProcess.allPhases();
        recruitmentProcess = recruitmentProcessRepository.save(recruitmentProcess);
        for (Phase phase : list){
            phase.setRecruitmentprocess(recruitmentProcess);
            phaseRepository.save(phase);
        }
        return true;
    }

}
