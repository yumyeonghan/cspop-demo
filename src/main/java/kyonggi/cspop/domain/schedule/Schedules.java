package kyonggi.cspop.domain.schedule;


import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import kyonggi.cspop.domain.schedule.enums.Step;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Schedules extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("단계")
    @Enumerated(EnumType.STRING)
    private Step step;

    @Comment("시작 일정")
    private LocalDate startDate;

    @Comment("종료 일정")
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private ScheduleState scheduleState;

}
