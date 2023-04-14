package kyonggi.cspop.domain.board.excel.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ExcelBoardSubmitFormDto {

    @NotBlank(message = "값을 입력하세요") @NotNull
    private String professorName;

    @NotBlank(message = "값을 입력하세요") @NotNull
    private String graduationDate;

    @NotBlank(message = "값을 입력하세요") @NotNull
    private String capstoneCompletion;
}
