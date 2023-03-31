package kyonggi.cspop.application.controller.board.userstatus.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserScheduleDto {

    private String step;
    private LocalDate startDate;
    private LocalDate endDate;
    private String submitStatus;
    private String approvalStatus;

    public UserScheduleDto(String step, LocalDate startDate, LocalDate endDate, String submitStatus, String approvalStatus) {
        this.step = step;
        this.startDate = startDate;
        this.endDate = endDate;
        this.submitStatus = submitStatus;
        this.approvalStatus = approvalStatus;
    }
}
