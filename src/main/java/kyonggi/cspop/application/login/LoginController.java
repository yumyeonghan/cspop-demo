package kyonggi.cspop.application.login;

import kyonggi.cspop.application.login.dto.LoginDto;
import kyonggi.cspop.domain.login.LoginService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static kyonggi.cspop.application.SessionFactory.CSPOP_SESSION_KEY;

@RestController
@RequiredArgsConstructor
@RequestMapping(("/api"))
@Slf4j
public class LoginController {
    private final LoginService loginService;

    @PostMapping("/login/auth")
    public String login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request) {
        UserSessionDto userSession = loginService.login(loginDto.getStudentId(), loginDto.getStudentPassword());
        HttpSession session = request.getSession();
        session.setAttribute(CSPOP_SESSION_KEY, userSession);
        return "/api/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if(Objects.nonNull(session)) {
            session.invalidate();
        }

        return "/api/home";
    }
}
