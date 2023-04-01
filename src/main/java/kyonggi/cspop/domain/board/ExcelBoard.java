package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.form.proposalform.ProposalForm;
import kyonggi.cspop.domain.form.submitform.SubmitForm;
import kyonggi.cspop.domain.form.submitform.enums.GraduationRequirements;
import kyonggi.cspop.domain.schedule.enums.Step;
import kyonggi.cspop.domain.users.Users;
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

    public static ExcelBoard createExcelBoard(Row row) {
        ExcelBoard excelBoard = new ExcelBoard();
        excelBoard.studentId = row.getCell(0).getStringCellValue();
        excelBoard.studentName = row.getCell(1).getStringCellValue();
        excelBoard.professorName = row.getCell(2).getStringCellValue();
        excelBoard.graduationDate = row.getCell(3).getStringCellValue();
        excelBoard.step = row.getCell(4).getStringCellValue();
        excelBoard.state = row.getCell(5).getStringCellValue();
        excelBoard.otherQualifications = row.getCell(6).getStringCellValue();
        excelBoard.capstoneCompletion = row.getCell(7).getStringCellValue();
        return excelBoard;
    }

    public static ExcelBoard addExcelBySubmitForm(Users users, SubmitForm submitForm) {

        ExcelBoard excelBoard = new ExcelBoard();
        excelBoard.studentId = users.getStudentId();
        excelBoard.studentName = users.getStudentName();
        excelBoard.professorName = "미정";
        excelBoard.graduationDate = "미확인";
        excelBoard.step = Step.RECEIVED.getStepToString();
        excelBoard.state = "미승인";

        if (submitForm.getGraduationRequirements().getGraduationRequirementsToString().equals("기타자격")) {
            excelBoard.otherQualifications = GraduationRequirements.Other_Qualifications.getGraduationRequirementsToString();
        }
        else {
            excelBoard.otherQualifications = GraduationRequirements.THESIS.getGraduationRequirementsToString();
        }
        excelBoard.capstoneCompletion = "미확인";
        return excelBoard;
    }

    public void updateExcelByProposalForm() {
        this.step = Step.PROPOSAL.getStepToString();
        this.state = "미승인";
    }

    public void updateExcelByInterimForm() {
        this.step = Step.INTERIM_REPORT.getStepToString();
        this.state = "미승인";
    }

}