package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
public class CertificationBoard extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Comment("소속 학과")
    private String department;

    @Comment("학번")
    private String studentId;

    @Comment("학생 이름")
    private String studentName;

    @Comment("현재 학기")
    private Integer currentSemester;

    @Comment("전문교양 학점")
    private Integer professionalEducation;

    @Comment("MSC/BSM 학점")
    private Integer mscBsm;

    @Comment("설계 학점")
    private Float design;

    @Comment("전공 학점")
    private Integer major;

    @Comment("필수 과목")
    private String essential;

    @Comment("선/후수 과목")
    private String firstAndLast;

    @Comment("총 학점")
    private Integer total;

    @Comment("특이 사항")
    private String specialNote;
}
