package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.uploadfile.InterimFormUploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterimFormUploadFileRepository extends JpaRepository<InterimFormUploadFile, Long> {

}
