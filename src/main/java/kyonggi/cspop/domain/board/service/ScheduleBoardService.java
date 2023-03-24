package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.application.controller.board.schedule.dto.ScheduleDto;
import kyonggi.cspop.domain.board.ScheduleBoard;
import kyonggi.cspop.domain.board.repository.ScheduleBoardRepository;
import kyonggi.cspop.domain.board.repository.ScheduleRepository;
import kyonggi.cspop.domain.schedule.Schedules;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ScheduleBoardService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleBoardRepository scheduleBoardRepository;

    public List<Schedules> findScheduleList() {
        return scheduleRepository.findAll();
    }

    public Schedules findByScheduleId(Long id) {
        return scheduleRepository.findById(id).get();
    }

    @Transactional
    public void updateSchedules(Long id, ScheduleDto scheduleDto) {
        Schedules schedules = findByScheduleId(id);
        schedules.updateInfo(scheduleDto);
    }

    public List<ScheduleBoard> findScheduleBoardList() {
        return scheduleBoardRepository.findAll();
    }

    //AJAX 통신용 API Service logic
    @Transactional
    public void updateReceivedText(String receivedText) {
        scheduleBoardRepository.findById(1l).get().updateReceivedText(receivedText);
    }

    @Transactional
    public void updateProposalText(String proposalText) {
        scheduleBoardRepository.findById(1l).get().updateProposalText(proposalText);
    }

    @Transactional
    public void updateInterimReportText(String interimReportText) {
        scheduleBoardRepository.findById(1l).get().updateInterimReportText(interimReportText);
    }

    @Transactional
    public void updateFinalReportText(String finalReportText) {
        scheduleBoardRepository.findById(1l).get().updateFinalReportText(finalReportText);
    }

    @Transactional
    public void updateFinalPassText(String finalPassText) {
        scheduleBoardRepository.findById(1l).get().updateFinalPassText(finalPassText);
    }

    @Transactional
    public void updateOtherQualificationsText(String otherQualificationsText) {
        scheduleBoardRepository.findById(1l).get().updateOtherQualificationsText(otherQualificationsText);
    }

    //Schedules 자동 업데이트 로직
    @Transactional
    public void autoUpdateSchedulesState() {
        List<Schedules> findScheduleList = scheduleRepository.findAll();
        findScheduleList.stream().forEach(e->e.updateScheduleState());
    }
}
