package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.board.ExcelBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 엑셀 다운로드 레포지토리-미완
 */
@Repository
public interface ExcelBoardRepository extends JpaRepository<ExcelBoard,Long> {
}
