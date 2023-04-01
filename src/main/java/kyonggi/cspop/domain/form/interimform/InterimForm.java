package kyonggi.cspop.domain.form.interimform;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.uploadfile.InterimFormUploadFile;
import kyonggi.cspop.domain.users.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterimForm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private boolean approval;
    @Column
    private String title;
    @Column
    private String division;
    @Column
    private String text;
    @Column
    private String plan;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "interimForm")
    private Users users;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "interimFormUploadFile_id", foreignKey = @ForeignKey(name = "fk_interim_form_upload_file_to_interim_form"))
    private InterimFormUploadFile interimFormUploadFile;

    public void designateUsers(Users users) {
        this.users = users;
    }

    public static InterimForm createInterimForm(boolean approval, String title, String division, String text, String plan) {

        InterimForm interimForm = new InterimForm();
        interimForm.approval = approval;
        interimForm.title = title;

        if (division.equals("option1")) {
            interimForm.division = "구현논문";
        }
        else{
            interimForm.division = "조사(이론)논문";
        }

        interimForm.text = text;
        interimForm.plan = plan;

        return interimForm;
    }
}
