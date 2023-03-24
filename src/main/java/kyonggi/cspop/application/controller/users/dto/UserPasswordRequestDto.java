package kyonggi.cspop.application.controller.users.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class UserPasswordRequestDto {

    @NotBlank(message = "비밀번호 입력은 필수입니다")
    private String studentPassword;

}
