package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.board.ExcelBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelBoardRepository extends JpaRepository<ExcelBoard,Long> {
}
