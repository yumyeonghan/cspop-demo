package kyonggi.cspop.application.guide.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class GuidanceBoardDto {

    @NotBlank(message = "값을 입력해주세요.")
    private final String text;

    private GuidanceBoardDto(String text){
        this.text=text;
    }
}
