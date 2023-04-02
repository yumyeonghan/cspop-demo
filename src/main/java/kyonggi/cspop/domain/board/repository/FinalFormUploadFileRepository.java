package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.uploadfile.FinalFormUploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalFormUploadFileRepository extends JpaRepository<FinalFormUploadFile, Long> {
}
