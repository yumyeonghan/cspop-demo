package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
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
        excelBoard.studentName =  row.getCell(1).getStringCellValue();
        excelBoard.professorName = row.getCell(2).getStringCellValue();
        excelBoard.graduationDate = row.getCell(3).getStringCellValue();
        excelBoard.step =  row.getCell(4).getStringCellValue();
        excelBoard.state =  row.getCell(5).getStringCellValue();
        excelBoard.otherQualifications = row.getCell(6).getStringCellValue();
        excelBoard.capstoneCompletion = row.getCell(7).getStringCellValue();
        return excelBoard;
    }
}
