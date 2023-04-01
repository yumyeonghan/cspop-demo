package kyonggi.cspop.domain.uploadfile;

import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.form.interimform.InterimForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterimFormUploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저가 업로드한 파일명")
    private String uploadFileName;

    @Comment("서버 내부에서 관리하는 파일명")
    private String storeFileName;
    @OneToOne(mappedBy = "interimFormUploadFile")
    private InterimForm interimForm;

    public void designateNoticeBoard(InterimForm interimForm) {

        this.interimForm = interimForm;
    }

    public InterimFormUploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }
}
