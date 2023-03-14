package kyonggi.cspop.domain.schedule.dto;

import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
public class ScheduleDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private final LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private final LocalDate endDate;

    private final ScheduleState state;

    private ScheduleDto(LocalDate startDate, LocalDate endDate) {
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
        this.startDate = startDate;
        this.endDate = endDate;
        this.state = state;
    }
}
