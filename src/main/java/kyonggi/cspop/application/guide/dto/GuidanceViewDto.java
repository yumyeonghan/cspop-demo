package kyonggi.cspop.application.guide.dto;
import kyonggi.cspop.domain.board.GuidanceBoard;
import lombok.Data;

@Data
public class GuidanceViewDto {

    Long id;

    String text;

    public GuidanceViewDto(GuidanceBoard guidanceBoard) {

        this.id = guidanceBoard.getId();
        this.text = guidanceBoard.getText();
    }
}
