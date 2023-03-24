package kyonggi.cspop.application.controller.board.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
