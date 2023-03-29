package kyonggi.cspop.application.controller.board.userstatus;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserDetailDto;
import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.service.ExcelBoardService;
import kyonggi.cspop.domain.board.service.ScheduleBoardService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.repository.UsersRepository;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/userStatus")
public class UserStatusController {

    private final UsersService usersService;
    private final UsersRepository usersRepository;
    private final ExcelBoardService excelBoardService;
    private final ScheduleBoardService scheduleBoardService;

    @GetMapping
    public String userStatusHome(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false)UserSessionDto userSessionDto,
                                 Model model) {
        Users user = usersRepository.findByStudentId(userSessionDto.getStudentId()).get();

        //유저의 submitForm 만들면 리팩토링 해야
        UserDetailDto userDetailDto = new UserDetailDto(user.getStudentId(),
                user.getStudentName(),
                user.getDepartment());
        model.addAttribute("userDetail", userDetailDto);

        return "graduation/userstatus/userGraduationStatus";
    }
}
