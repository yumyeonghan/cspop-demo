package kyonggi.cspop.test;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller

//사용x
public class ControllerTest {

    //jquery 테스트
    @ResponseBody
    @GetMapping("/test")
    public String test() {
        String text = "test";
        return text;
    }

    //jsp 테스트
    @GetMapping(value = "/main")
    public String home(Model model) throws Exception {
        model.addAttribute("name", "jeffrey");

        return "index";
    }

    //Json 응답 테스트
    @ResponseBody
    @GetMapping("/json-test")
    public User jsonTest() {
        return new User("유명한", 24, "19991013", "남자");
    }

    //Json 리스트 응답 테스트
    @ResponseBody
    @GetMapping("/json-list-test")
    public List<User> jsonListTest() {

        List<User> users = new ArrayList<>();
        users.add(new User("유명한", 24, "19991013", "남자"));
        users.add(new User("함현준", 24, "19991231", "남자"));
        users.add(new User("한상범", 24, "19991312", "여자"));
        users.add(new User("홍찬의", 24, "19996549", "여자"));

        return users;
    }
}

