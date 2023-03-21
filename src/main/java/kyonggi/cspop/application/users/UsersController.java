package kyonggi.cspop.application.users;

import kyonggi.cspop.application.users.dto.UsersDto;
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

    @PostMapping("/passwordQuestion")
    public ResponseEntity<Void> checkUserId(@NotBlank @RequestBody Map<String, String> studentId) {
        usersService.checkExistStudentNumber(studentId.get("studentId"));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/editPassword")
    public String updatePassword(@Validated @RequestBody Map<String, String> answerPw) {

        usersService.checkExistPasswordAnswer(answerPw.get("answerPw"));

        return "/api/home";
    }
}