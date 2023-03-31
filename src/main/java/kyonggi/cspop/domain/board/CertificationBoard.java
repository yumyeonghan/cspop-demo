package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter @Setter
public class CertificationBoard extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("소속 학과")
    private String department;

    @Comment("학번")
    private String studentId;

    @Comment("학생 이름")
    private String studentName;

    @Comment("현재 학기")
    private String currentSemester;

    @Comment("전문교양 학점")
    private String professionalEducation;

    @Comment("MSC/BSM 학점")
    private String mscBsm;

    @Comment("설계 학점")
    private String design;

    @Comment("전공 학점")
    private String major;

    @Comment("필수 과목")
    private String essential;

    @Comment("선/후수 과목")
    private String firstAndLast;

    @Comment("총 학점")
    private String total;

    @Comment("특이사항")
    private String specialNote;

    public static CertificationBoard createCertificationBoard(Row row) {
        CertificationBoard certificationBoard = new CertificationBoard();
        certificationBoard.department = row.getCell(0).getStringCellValue();
        certificationBoard.studentId = row.getCell(1).getStringCellValue();
        certificationBoard.studentName =  row.getCell(2).getStringCellValue();
        certificationBoard.currentSemester = row.getCell(3).getStringCellValue();
        certificationBoard.professionalEducation = row.getCell(4).getStringCellValue();
        certificationBoard.mscBsm = row.getCell(5).getStringCellValue();
        certificationBoard.design =  row.getCell(6).getStringCellValue();
        certificationBoard.major = row.getCell(7).getStringCellValue();
        certificationBoard.essential = row.getCell(8).getStringCellValue();
        certificationBoard.firstAndLast = row.getCell(9).getStringCellValue();
        certificationBoard.total =  row.getCell(10).getStringCellValue();
        certificationBoard.specialNote = row.getCell(11).getStringCellValue();
        return certificationBoard;
    }
}
