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

    @GetMapping("api/passwordReset")
    public String resetPassword(){
        return "account/passwordReset";
    }

    @GetMapping("api/excel")
    public String excel() {return "excel/excelIndex";}
    
    @GetMapping("api/graduation/form")
    public String noticeForm() {return "graduation/notice/noticeForm";}
    @GetMapping("api/graduation/detail")
    public String noticeDetail() {return "graduation/notice/noticeDetail";}
}
