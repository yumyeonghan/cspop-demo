package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.board.GuidanceBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidanceBoardRepository extends JpaRepository<GuidanceBoard, Long> {
}
