package kyonggi.cspop.domain.uploadfile;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinalFormUploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저가 업로드한 파일명")
    private String uploadFileName;
    @Comment("서버 내부에서 관리하는 파일명")
    private String storeFileName;

    public FinalFormUploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void updateFile(FinalFormUploadFile finalFormUploadFile) {
        this.uploadFileName = finalFormUploadFile.getUploadFileName();
        this.storeFileName = finalFormUploadFile.getStoreFileName();
    }
}
