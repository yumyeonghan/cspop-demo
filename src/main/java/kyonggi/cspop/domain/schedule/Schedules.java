package kyonggi.cspop.domain.schedule;

import com.fasterxml.jackson.annotation.JsonFormat;
import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.schedule.dto.ScheduleDto;
import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import kyonggi.cspop.domain.schedule.enums.Step;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
public class Schedules extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("단계")
    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private Step step;

    @Comment("시작 일정")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @Comment("종료 일정")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    //추후 수정 로직 제안
    @Column(updatable = false)
    private ScheduleState scheduleState;

    public void  updateInfo(ScheduleDto scheduleDto) {
        this.startDate = scheduleDto.getStartDate();
        this.endDate = scheduleDto.getEndDate();
        this.scheduleState = scheduleDto.getState();
    }
}
