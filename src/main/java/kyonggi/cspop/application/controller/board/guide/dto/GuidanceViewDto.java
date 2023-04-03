package kyonggi.cspop.application.controller.board.guide.dto;
import kyonggi.cspop.domain.board.guidance.GuidanceBoard;
import lombok.Data;

@Data
public class GuidanceViewDto {

    private Long id;
    private String text;

    public GuidanceViewDto(GuidanceBoard guidanceBoard) {
        this.id = guidanceBoard.getId();
        this.text = guidanceBoard.getText();
    }
}
