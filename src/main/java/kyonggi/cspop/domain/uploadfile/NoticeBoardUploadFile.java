package kyonggi.cspop.domain.uploadfile;

import kyonggi.cspop.domain.board.NoticeBoard;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter @Setter
public class NoticeBoardUploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("관리자가 업로드한 파일명")
    private String uploadFileName;
    @Comment("서버 내부에서 관리하는 파일명")
    private String storeFileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeBoard_id", foreignKey = @ForeignKey(name = "fk_notice_board_upload_file_to_notice_board"))
    private NoticeBoard noticeBoard;
}
