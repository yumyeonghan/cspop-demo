package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.ScheduleBoard;
import kyonggi.cspop.domain.board.repository.ScheduleBoardRepository;
import kyonggi.cspop.domain.board.repository.ScheduleRepository;
import kyonggi.cspop.domain.schedule.Schedules;
import kyonggi.cspop.domain.schedule.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleBoardService {

    private final ScheduleRepository scheduleRepository;

    private final ScheduleBoardRepository scheduleBoardRepository;

    public List<Schedules> findScheduleList() {

        return scheduleRepository.findAll();
    }

    public List<ScheduleBoard> findScheduleBoardList(){

        return scheduleBoardRepository.findAll();
    }

    public Schedules findById(Long id){

        return scheduleRepository.findById(id).get();
    }

    public void save(Schedules schedules) {
        scheduleRepository.save(schedules);
    }

    @Transactional
    public void update(Long id, ScheduleDto scheduleDto) {

        Schedules schedules = findById(id);
        schedules.updateInfo(scheduleDto);
        save(schedules);
    }
}
