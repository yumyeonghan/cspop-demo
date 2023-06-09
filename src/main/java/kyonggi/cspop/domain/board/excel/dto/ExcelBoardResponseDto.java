package kyonggi.cspop.domain.board.excel.dto;

import kyonggi.cspop.domain.board.excel.ExcelBoard;
import lombok.Data;

@Data
public class ExcelBoardResponseDto {

    private Long id;

    private String studentId;

    private String studentName;

    private String professorName;

    private String graduationDate;

    private String step;

    private String state;

    private String otherQualifications;

    private String capstoneCompletion;


    public ExcelBoardResponseDto(ExcelBoard excelBoard) {
        this.id = excelBoard.getId();
        this.studentId = excelBoard.getStudentId();
        this.studentName = excelBoard.getStudentName();
        this.professorName = excelBoard.getProfessorName();
        this.graduationDate = excelBoard.getGraduationDate();
        this.step = excelBoard.getStep();
        this.state = excelBoard.getState();
        this.otherQualifications = excelBoard.getQualifications();
        this.capstoneCompletion = excelBoard.getCapstoneCompletion();
    }
}
