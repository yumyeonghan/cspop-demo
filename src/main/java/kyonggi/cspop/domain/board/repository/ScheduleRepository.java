package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.schedule.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 진행 일정 테이블 날짜 수정 레포지토리
 */
@Repository
public interface ScheduleRepository extends JpaRepository<Schedules,Long> {
}

