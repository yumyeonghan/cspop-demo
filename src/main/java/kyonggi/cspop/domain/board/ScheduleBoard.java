package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter @Setter
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
}
