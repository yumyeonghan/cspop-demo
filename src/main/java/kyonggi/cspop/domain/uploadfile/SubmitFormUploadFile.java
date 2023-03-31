package kyonggi.cspop.domain.uploadfile;

import kyonggi.cspop.domain.form.submitform.SubmitForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitFormUploadFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저가 업로드한 파일명")
    private String uploadFileName;
    @Comment("서버 내부에서 관리하는 파일명")
    private String storeFileName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submitForm_id", foreignKey = @ForeignKey(name = "fk_submit_form_upload_file_to_notice_board"))
    private SubmitForm submitForm;

    public void designateSubmitForm(SubmitForm submitForm) {
        this.submitForm = submitForm;
    }
}
