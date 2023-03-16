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

    public Schedules findById(Long id){
        return scheduleRepository.findById(id).get();
    }

    @Transactional
    public void save(Schedules schedules) {
        scheduleRepository.save(schedules);
    }

    @Transactional
    public void update(Long id, ScheduleDto scheduleDto) {
        Schedules schedules = findById(id);
        schedules.updateInfo(scheduleDto);
    }

    //ScheduleBoard 로직
    public List<ScheduleBoard> findScheduleBoardList(){
        return scheduleBoardRepository.findAll();
    }

    public ScheduleBoard findById_board(Long id) {
        return scheduleBoardRepository.findById(id).get();
    }

    @Transactional
    public void save_board(ScheduleBoard scheduleBoard) {
        scheduleBoardRepository.save(scheduleBoard);
    }

    @Transactional
    public void update_board(Long id, ScheduleBoardDto scheduleBoardDto) {
        ScheduleBoard scheduleBoard = findById_board(id);
        scheduleBoard.updateInfo(scheduleBoardDto);
    }
}
