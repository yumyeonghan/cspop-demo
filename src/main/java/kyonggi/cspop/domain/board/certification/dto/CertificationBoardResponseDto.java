package kyonggi.cspop.domain.board.certification.dto;

import kyonggi.cspop.domain.board.certification.CertificationBoard;
import lombok.Data;

@Data
public class CertificationBoardResponseDto {

    private Long id;

    private String department;

    private String studentId;

    private String studentName;

    private String currentSemester;

    private String professionalEducation;

    private String mscBsm;

    private String design;

    private String major;

    private String essential;

    private String firstAndLast;

    private String total;

    private String specialNote;

    public CertificationBoardResponseDto(CertificationBoard certificationBoard) {
        this.id = certificationBoard.getId();
        this.department = certificationBoard.getDepartment();
        this.studentId = certificationBoard.getStudentId();
        this.studentName = certificationBoard.getStudentName();
        this.currentSemester = certificationBoard.getCurrentSemester();
        this.professionalEducation = certificationBoard.getProfessionalEducation();
        this.mscBsm = certificationBoard.getMscBsm();
        this.design = certificationBoard.getDesign();
        this.major = certificationBoard.getMajor();
        this.essential = certificationBoard.getEssential();
        this.firstAndLast = certificationBoard.getFirstAndLast();
        this.total = certificationBoard.getTotal();
        this.specialNote = certificationBoard.getSpecialNote();
    }
}
