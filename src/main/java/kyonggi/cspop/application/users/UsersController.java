package kyonggi.cspop.application.users;

import kyonggi.cspop.application.users.dto.UsersDto;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;
import java.net.URI;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/user")
    public ResponseEntity<Void> signUp(@Validated @RequestBody UsersDto usersDto) {
        usersService.saveUser(usersDto.toEntity());
        return ResponseEntity.created(URI.create("/")).build();
    }

    @PostMapping("/user/duplicate-check")
    public ResponseEntity<Void> duplicateCheckInSignUp (@NotBlank @RequestBody Map<String, String> studentId) {
        usersService.checkDuplicateStudentNumber(studentId.get("studentId"));
        return ResponseEntity.noContent().build();
    }
}
