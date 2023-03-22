package kyonggi.cspop.application.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kyonggi.cspop.domain.schedule.enums.ScheduleState;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ScheduleDto {

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "값을 입력하세요")
    private LocalDate startDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "값을 입력하세요")
    private LocalDate endDate;
}
