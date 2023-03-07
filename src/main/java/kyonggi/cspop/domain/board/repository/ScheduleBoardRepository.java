package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.board.ScheduleBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 진행 일정 테이블 관련 게시판 수정 레포지토리
 */
@Repository
public interface ScheduleBoardRepository extends JpaRepository<ScheduleBoard,Long> {
 }
