package kyonggi.cspop.domain.form.submitform;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.form.submitform.enums.GraduationRequirements;
import kyonggi.cspop.domain.uploadfile.SubmitFormUploadFile;
import kyonggi.cspop.domain.users.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubmitForm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("학번")
    @Column(nullable = false)
    private String studentId;

    @Comment("학생 이름")
    @Column(nullable = false)
    private String studentName;

    @Comment("소속학과")
    @Column(nullable = false)
    private String department;

    @Comment("졸업날짜")
    @Column(nullable = false)
    private LocalDate graduationDate;

    @Comment("승인여부")
    @Column(nullable = false)
    private boolean approval;

    @Enumerated(EnumType.STRING)
    private GraduationRequirements graduationRequirements;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "submitForm")
    private Users users;

    @OneToOne(mappedBy = "submitForm", cascade = CascadeType.ALL, orphanRemoval = true)
    private SubmitFormUploadFile submitFormUploadFile;

    public void designateUsers(Users users) {
        this.users = users;
    }

    // 양방향 연관관계 편의 메소드
    public void addUploadFile(SubmitFormUploadFile uploadFile) {
        uploadFile.designateSubmitForm(this);
        this.submitFormUploadFile = uploadFile;
    }
}
