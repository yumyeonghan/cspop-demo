package kyonggi.cspop.application.users;

import kyonggi.cspop.application.users.dto.UsersDto;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Void> duplicateCheckInSignUp (@NotBlank @RequestBody Map<String, String> studentId) {
        usersService.checkDuplicateStudentNumber(studentId.get("studentId"));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/login")
    public ResponseEntity<Void> login () {
        return ResponseEntity.noContent().build();
    }
}
