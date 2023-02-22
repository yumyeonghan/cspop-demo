package kyonggi.cspop.application.users.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.enums.Classification;
import kyonggi.cspop.domain.users.enums.Sex;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UsersDto {

    @NotBlank(message = "학번 입력은 필수입니다")
    private String studentId;

    @NotBlank(message = "비밀번호 입력은 필수입니다")
    private String studentPassword;

    @NotBlank(message = "이름 입력은 필수입니다")
    private String studentName;

    @NotNull(message = "성별 선택은 필수입니다")
    private String sex;

    @NotNull(message = "생일 입력은 필수입니다")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @NotBlank(message = "이메일 입력은 필수입니다")
    private String email;

    @NotBlank(message = "번호 입력은 필수입니다")
    private String phoneNumber;

    @NotNull(message = "희망구분 선택은 필수입니다")
    private String classification;

    @NotBlank(message = "학과 입력은 필수입니다")
    private String department;

    public Users toEntity() {
        return Users.createUser(studentId,
                studentPassword,
                studentName,
                Sex.toSex(sex),
                birth,
                email,
                phoneNumber,
                Classification.toClassification(classification),
                department);
    }
}
