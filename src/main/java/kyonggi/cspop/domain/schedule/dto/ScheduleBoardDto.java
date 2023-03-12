package kyonggi.cspop.domain.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleBoardDto {

    private final String receivedText;

    private final String proposalText;

    private final String interimReportText;

    private final String finalReportText;

    private final String finalPassText;

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
