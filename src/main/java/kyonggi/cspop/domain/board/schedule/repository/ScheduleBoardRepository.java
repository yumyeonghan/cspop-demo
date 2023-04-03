package kyonggi.cspop.domain.board.schedule.repository;

import kyonggi.cspop.domain.board.schedule.ScheduleBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleBoardRepository extends JpaRepository<ScheduleBoard,Long> {
 }
