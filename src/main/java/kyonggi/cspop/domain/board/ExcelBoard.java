package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ExcelBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("학번")
    private String studentId;
    @Comment("학생 이름")
    private String studentName;
    @Comment("교수 이름")
    private String professorName;
    @Comment("학생 졸업날짜")
    private String graduationDate;
    @Comment("단계")
    private String step;
    @Comment("상태")
    private String state;
    @Comment("기타 자격")
    private String otherQualifications;
    @Comment("캡스톤 이수")
    private String capstoneCompletion;
}
