package kyonggi.cspop.application.schedule.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ScheduleBoardDto {

    @NotBlank(message = "값을 입력해주세요.")
    private final String receivedText;
    @NotBlank(message = "값을 입력해주세요.")
    private final String proposalText;
    @NotBlank(message = "값을 입력해주세요.")
    private final String interimReportText;
    @NotBlank(message = "값을 입력해주세요.")
    private final String finalReportText;
    @NotBlank(message = "값을 입력해주세요.")
    private final String finalPassText;
    @NotBlank(message = "값을 입력해주세요.")
    private final String otherQualificationsText;

    private ScheduleBoardDto(String receivedText,String proposalText,String interimReportText,
                             String finalReportText,String finalPassText,String otherQualificationsText){

        this.receivedText=receivedText;
        this.proposalText=proposalText;
        this.interimReportText=interimReportText;
        this.finalReportText=finalReportText;
        this.finalPassText=finalPassText;
        this.otherQualificationsText=otherQualificationsText;
    }
}
