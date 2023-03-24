package kyonggi.cspop.application.controller.board.guide.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class GuidanceBoardRequestDto {

    @NotBlank(message = "내용을 입력하셔야 합니다!")
    private String text;
}
