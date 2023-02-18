package kyonggi.cspop.domain.otherqualifications;

import kyonggi.cspop.domain.submitform.SubmitForm;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class OtherQualifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("기타자격")
    private String Qualification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "submitForm_id", foreignKey = @ForeignKey(name = "fk_other_qualifications_to_submit_form"))
    private SubmitForm submitForm;

}