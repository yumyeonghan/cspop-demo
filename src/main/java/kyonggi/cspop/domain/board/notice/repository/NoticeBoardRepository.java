package kyonggi.cspop.domain.board.notice.repository;

import kyonggi.cspop.domain.board.notice.NoticeBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    Page<NoticeBoard> findAllByOrderByFixedDescIdDesc(Pageable pageable);
    Page<NoticeBoard> findByTitleContainingOrderByFixedDescIdDesc(String title, Pageable pageable);
}