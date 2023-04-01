package kyonggi.cspop.domain.form.finalform;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.uploadfile.FinalFormUploadFile;
import kyonggi.cspop.domain.users.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FinalForm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "finalForm")
    private Users users;

    @Column
    private boolean approval;

    @Column
    private String title;

    @Column
    private String division;

    @Column
    private String qualification;

    @Column
    private Integer pageNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "finalFormUploadFile_id", foreignKey = @ForeignKey(name = "fk_final_form_upload_file_to_final_form"))
    private FinalFormUploadFile finalFormUploadFile;

    public void designateUsers(Users users) {
        this.users = users;
    }

}
