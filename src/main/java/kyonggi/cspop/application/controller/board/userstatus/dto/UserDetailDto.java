package kyonggi.cspop.application.controller.board.userstatus.dto;

import kyonggi.cspop.domain.otherqualifications.OtherQualifications;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserDetailDto {

    private String studentId;
    private String graduationDate;
    private String studentName;
    private String department;
    private String otherQualifications;
    private String advisor;

    public UserDetailDto(String studentId,
                         String studentName,
                         String department) {

        this.studentId = studentId;
        this.graduationDate = "신청서 만들면";
        this.studentName = studentName;
        this.department = department;
        this.otherQualifications = "신청서 만들면";
        this.advisor = "김도훈";
    }
}
