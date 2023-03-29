package kyonggi.cspop.application.controller.board.userstatus;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/userStatus")
public class UserStatusController {

    @GetMapping
    public String userStatusHome() {
        return "graduation/userstatus/userGraduationStatus";
    }
}
