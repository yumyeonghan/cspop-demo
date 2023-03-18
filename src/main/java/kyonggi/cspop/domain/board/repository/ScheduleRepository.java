package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.schedule.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleRepository extends JpaRepository<Schedules,Long> {
}

