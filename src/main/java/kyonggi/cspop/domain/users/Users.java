package kyonggi.cspop.domain.users;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.submitform.SubmitForm;
import kyonggi.cspop.domain.users.enums.Classification;
import kyonggi.cspop.domain.users.enums.Sex;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "submit_form_id_unique",columnNames = "submitForm_id")})
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //===========회원 가입============//
    @Comment("학번")
    @Column(nullable = false)
    private String studentId;

    @Comment("학생 비밀번호")
    @Column(nullable = false)
    private String studentPassword;

    @Comment("학생 이름")
    @Column(nullable = false)
    private String studentName;

    @Comment("성별")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    @Comment("생일")
    @Column(nullable = false)
    private LocalDate birth;

    @Comment("이메일")
    @Column(nullable = false)
    private String email;

    @Comment("전화번호")
    @Column(nullable = false)
    private String phoneNumber;

    @Comment("신분")
    @Enumerated(EnumType.STRING)
    private Classification classification;

    @Comment("소속학과")
    @Column(nullable = false)
    private String department;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "submitForm_id", foreignKey = @ForeignKey(name = "fk_users_to_submit_form"))
    private SubmitForm submitForm;

    // 양방향 연관관계 편의 메소드
    public void addSubmitForms(SubmitForm submitForm) {
        this.submitForm = submitForm;
        submitForm.designateUsers(this);
    }
}
