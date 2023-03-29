package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.board.CertificationBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificationBoardRepository extends JpaRepository<CertificationBoard, Long> {
    Page<CertificationBoard> findAllByOrderById(Pageable pageable);
}

