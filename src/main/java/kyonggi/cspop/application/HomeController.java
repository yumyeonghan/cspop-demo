package kyonggi.cspop.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("api/home")
    public String home() {
        return "index";
    }

    @GetMapping("api/login")
    public String login() {
        return "account/login";
    }

    @GetMapping("api/signup")
    public String signup() {
        return "account/signup";
    }

    @GetMapping("api/graduation/notice")
    public String notice() {
        return "graduation/notice";
    }

    @GetMapping("api/excel")
    public String excel() {return "excel/excelIndex";}

    @GetMapping("/graduation/progress_schedule")
    public String schedule(){
        return "graduation/progress_schedule";
    }
}
