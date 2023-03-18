package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.ScheduleBoard;
import kyonggi.cspop.domain.board.repository.ScheduleBoardRepository;
import kyonggi.cspop.domain.board.repository.ScheduleRepository;
import kyonggi.cspop.domain.schedule.Schedules;
import kyonggi.cspop.application.schedule.dto.ScheduleBoardDto;
import kyonggi.cspop.application.schedule.dto.ScheduleDto;
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

    //Schedules 로직
    public List<Schedules> findScheduleList() {
        return scheduleRepository.findAll();
    }

    public Schedules findByScheduleId(Long id){
        return scheduleRepository.findById(id).get();
    }

    @Transactional
    public void saveSchedules(Schedules schedules) {
        scheduleRepository.save(schedules);
    }

    @Transactional
    public void updateSchedules(Long id, ScheduleDto scheduleDto) {
        Schedules schedules = findByScheduleId(id);
        schedules.updateInfo(scheduleDto);
    }

    //ScheduleBoard 로직
    public List<ScheduleBoard> findScheduleBoardList(){
        return scheduleBoardRepository.findAll();
    }

    public ScheduleBoard findByScheduleBoardId(Long id) {
        return scheduleBoardRepository.findById(id).get();
    }

    @Transactional
    public void updateScheduleBoard(Long id, ScheduleBoardDto scheduleBoardDto) {
        ScheduleBoard scheduleBoard = findByScheduleBoardId(id);
        scheduleBoard.updateInfo(scheduleBoardDto);
    }
}
