package kyonggi.cspop.application.controller.login;

import kyonggi.cspop.application.controller.login.dto.LoginDto;
import kyonggi.cspop.domain.login.LoginService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

import static kyonggi.cspop.application.SessionFactory.CSPOP_SESSION_KEY;

@Controller
@RequiredArgsConstructor
@RequestMapping(("/api"))
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String login() {
        return "account/login";
    }

    @ResponseBody
    @PostMapping("/login/auth")
    public String login(@Validated @RequestBody LoginDto loginDto, HttpServletRequest request) {
        UserSessionDto adminSession = loginService.loginAsAdmin(loginDto.getLoginId(), loginDto.getLoginPassword());
        if (!adminIsNull(adminSession)) {
            createAdminSession(request, adminSession);
        } else {
            UserSessionDto userSession = loginService.login(loginDto.getLoginId(), loginDto.getLoginPassword());
            createUserSession(request, userSession);
        }
        return "/api/home";
    }

    @ResponseBody
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (Objects.nonNull(session)) {
            session.invalidate();
        }

        return "/api/home";
    }

    private static void createUserSession(HttpServletRequest request, UserSessionDto userSession) {
        HttpSession session = request.getSession();
        session.setAttribute(CSPOP_SESSION_KEY, userSession);
    }

    private static boolean adminIsNull(UserSessionDto adminSession) {
        return adminSession.getStudentId().equals("null");
    }

    private static void createAdminSession(HttpServletRequest request, UserSessionDto adminSession) {
        createUserSession(request, adminSession);
    }
}
