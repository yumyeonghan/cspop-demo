package kyonggi.cspop.domain.schedule.dto;

import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ScheduleDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private final LocalDate endDate;
    private final ScheduleState state;


    private ScheduleDto(LocalDate startDate, LocalDate endDate, ScheduleState state) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }
}
