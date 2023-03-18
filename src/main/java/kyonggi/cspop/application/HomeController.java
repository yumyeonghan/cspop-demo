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

    @GetMapping("api/excel")
    public String excel() {return "excel/excelIndex";}
    
    @GetMapping("api/graduation/form")
    public String noticeForm() {return "graduation/noticeForm";}
    @GetMapping("api/graduation/detail")
    public String noticeDetail() {return "graduation/noticeDetail";}
}
