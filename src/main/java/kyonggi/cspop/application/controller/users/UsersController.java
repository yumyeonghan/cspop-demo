package kyonggi.cspop.application.controller.users;

import kyonggi.cspop.application.controller.users.dto.UsersDto;
import kyonggi.cspop.application.controller.users.dto.UserPasswordRequestDto;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    @GetMapping("api/signup")
    public String signup() {
        return "account/signup";
    }

    @GetMapping("api/passwordReset")
    public String resetPassword(){
        return "account/passwordReset";
    }

    @PostMapping("/user")
    public String signUp(@Validated @RequestBody UsersDto usersDto) {
        usersService.saveUser(usersDto.toEntity());
        return "/api/home";
    }

    @PostMapping("/user/duplicate-check")
    public ResponseEntity<Void> duplicateCheckInSignUp(@NotBlank @RequestBody Map<String, String> studentId) {
        usersService.checkDuplicateStudentNumber(studentId.get("studentId"));
        return ResponseEntity.noContent().build();
    }

    //학번 유무 체크
    @PostMapping("/passwordQuestion")
    public ResponseEntity<Void> checkUserId(@NotBlank @RequestBody Map<String, String> studentId) {

        usersService.checkExistStudentNumber(studentId.get("studentId"));
        return ResponseEntity.noContent().build();
    }

    //비밀번호 답 체크
    @PostMapping("/answerPassword")
    public ResponseEntity<Void> checkAnswerPw(@NotBlank @RequestBody Map<String, String> answerPw) {
        //answer 체크
        usersService.checkExistPasswordAnswer(answerPw.get("answerPw"));

        return ResponseEntity.noContent().build();
    }

    //학번에 따른 비밀번호 수정
    @PostMapping("/editPassword/{studentId}")
    public String updatePassword(@PathVariable String studentId, @NotBlank @RequestBody UserPasswordRequestDto userPasswordRequestDto) {

        //비밀번호 변경
        usersService.updatePassword(studentId, userPasswordRequestDto);
        return "/api/home";
    }
}