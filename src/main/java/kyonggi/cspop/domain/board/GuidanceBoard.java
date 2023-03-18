package kyonggi.cspop.domain.board;

import kyonggi.cspop.application.guide.dto.GuidanceBoardDto;
import kyonggi.cspop.domain.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GuidanceBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("안내 및 내규 본문")
    private String text;

    public void updateGuidance(GuidanceBoardDto guidanceBoardDto) {

        this.text = guidanceBoardDto.getText();
    }
}
