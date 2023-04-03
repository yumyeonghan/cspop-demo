package kyonggi.cspop.domain.board.guidance.repository;

import kyonggi.cspop.domain.board.guidance.GuidanceBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuidanceBoardRepository extends JpaRepository<GuidanceBoard, Long> {
}
