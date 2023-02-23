package kyonggi.cspop.domain.login.dto;

import kyonggi.cspop.domain.users.Users;
import lombok.Data;

@Data
public class UserSessionDto {

    private String studentId;
    private String studentName;
    private String classification;

    public UserSessionDto(Users user) {
        this.studentId = user.getStudentId();
        this.studentName = user.getStudentName();
        this.classification = user.getClassification().getClassificationToString();
    }
}
