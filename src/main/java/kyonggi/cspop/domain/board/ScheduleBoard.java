package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.schedule.dto.ScheduleBoardDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
public class ScheduleBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("신청접수 본문")
    private String receivedText;

    @Comment("제안서 본문")
    private String proposalText;

    @Comment("중간보고서 본문")
    private String interimReportText;

    @Comment("최종보고서 본문")
    private String finalReportText;

    @Comment("최종통과 본문")
    private String finalPassText;

    @Comment("기타자격 본문")
    private String otherQualificationsText;

    public void updateInfo(ScheduleBoardDto scheduleBoardDto) {
        this.receivedText = scheduleBoardDto.getReceivedText();
        this.proposalText = scheduleBoardDto.getProposalText();
        this.interimReportText = scheduleBoardDto.getInterimReportText();
        this.finalReportText = scheduleBoardDto.getFinalReportText();
        this.finalPassText = scheduleBoardDto.getFinalPassText();
        this.otherQualificationsText = scheduleBoardDto.getOtherQualificationsText();
    }
}
