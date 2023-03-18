package kyonggi.cspop.domain.schedule;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.application.schedule.dto.ScheduleDto;
import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import kyonggi.cspop.domain.schedule.enums.Step;
import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedules extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("단계")
    @Column(updatable = false)
    @Enumerated(EnumType.STRING)
    private Step step;

    @Comment("시작 일정")
    private LocalDate startDate;

    @Comment("종료 일정")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ScheduleState scheduleState;

    public void  updateInfo(ScheduleDto scheduleDto) {
        this.startDate = scheduleDto.getStartDate();
        this.endDate = scheduleDto.getEndDate();
        this.scheduleState = scheduleDto.getState();
    }
}
