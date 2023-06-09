package kyonggi.cspop.application.controller.login.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank(message = "아이디는 필수입니다")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수입니다")
    private String loginPassword;
}
