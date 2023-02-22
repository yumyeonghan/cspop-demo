package kyonggi.cspop.application.excel;

import kyonggi.cspop.domain.board.ExcelBoard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 엑셀 다운로드 레포지토리
 */
public interface ExcelRepository extends JpaRepository<ExcelBoard,Long> {
}
