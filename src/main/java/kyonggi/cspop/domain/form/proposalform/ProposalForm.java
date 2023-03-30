package kyonggi.cspop.domain.form.proposalform;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.users.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProposalForm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String studentId;
    @Column
    private String graduationDate;
    @Column
    private String advisor;
    @Column
    private String studentName;
    @Column
    private String department;
    @Column
    private String qualification;
    @Column
    private boolean approval;
    @Column
    private String title;
    @Column
    private String division;
    @Column
    private String keyword;
    @Column
    private String text;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "submitForm")
    private Users users;

    public void designateUsers(Users users) {
        this.users = users;
    }
}
