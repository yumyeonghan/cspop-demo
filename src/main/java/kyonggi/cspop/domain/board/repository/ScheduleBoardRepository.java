package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.board.ScheduleBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScheduleBoardRepository extends JpaRepository<ScheduleBoard,Long> {
 }
