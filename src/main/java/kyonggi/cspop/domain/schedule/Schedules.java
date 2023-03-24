package kyonggi.cspop.domain.schedule;

import kyonggi.cspop.application.schedule.dto.ScheduleDto;
import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import kyonggi.cspop.domain.schedule.enums.Step;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Slf4j
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
    private LocalDate startDate;

    @Comment("종료 일정")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ScheduleState scheduleState;

    public void updateInfo(ScheduleDto scheduleDto) {
        this.startDate = scheduleDto.getStartDate();
        this.endDate = scheduleDto.getEndDate();

        LocalDate now = LocalDate.now();
        ScheduleState state;

        if (now.isAfter(endDate)) {
            state = ScheduleState.valueOf("END");
        }
        else if (now.isBefore(startDate)){
            state = ScheduleState.valueOf("WAIT");
        }
        else{
            state = ScheduleState.valueOf("PROCEEDING");
        }
        this.scheduleState = state;
    }

    public void updateScheduleState() {
        LocalDate now= LocalDate.now();

        if (now.isAfter(endDate)) {
            this.scheduleState = ScheduleState.valueOf("END");
            System.out.println("scheduleState = " + scheduleState);
            log.info("End");
        }
        else if (now.isBefore(startDate)){
            this.scheduleState = ScheduleState.valueOf("WAIT");
            System.out.println("scheduleState = " + scheduleState);
            log.info("wait");

        }
        else{
            this.scheduleState = ScheduleState.valueOf("PROCEEDING");
            System.out.println("scheduleState = " + scheduleState);
            log.info("Proceeding");
        }
    }
}
