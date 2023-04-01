package kyonggi.cspop.application.controller.form.interimForm;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserDetailDto;
import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.service.ExcelBoardService;
import kyonggi.cspop.domain.board.service.form.InterimFormService;
import kyonggi.cspop.domain.form.interimform.InterimForm;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class InterimFormController {

    private final UsersService usersService;
    private final InterimFormService interimFormService;
    private final ExcelBoardService excelBoardService;


    @GetMapping("api/interimForm")
    public String saveInterimForm(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, Model model) {
        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());
        Optional<ExcelBoard> excelByStudentId = excelBoardService.findExcelByStudentId(user.getStudentId());
        String advisor = excelByStudentId.get().getProfessorName();
        UserDetailDto userDetailDto = new UserDetailDto(
                user.getStudentId(),
                excelByStudentId.get().getGraduationDate(),
                user.getStudentName(),
                user.getDepartment(),
                excelByStudentId.get().getProfessorName(),
                user.getSubmitForm());
        model.addAttribute("userDetail", userDetailDto);
        return "graduation/form/interimForm";
    }

    @PostMapping("api/interimForm")
    public String saveInterimFormProgress(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, @Validated @ModelAttribute InterimFormDto interimFormDto) {

        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());

        //중간 보고서 폼 등록
        InterimForm interimForm = InterimForm.createInterimForm(false, interimFormDto.getTitle(), interimFormDto.getDivision(), interimFormDto.getText(), interimFormDto.getPlan());
        interimFormService.saveInterimForm(interimForm);

        //유저 테이블 수정
        usersService.updateExcelByInterimForm(user, interimForm);

        //엑셀보드 업데이트
        excelBoardService.updateExcelByInterimForm(user);

        log.info("폼 = {}", interimFormDto);
        //신청 폼 저장 -> 액셀 업데이트 -> 졸업 진행 상황 테이블 업데이트 -> 신청자 리스트 업데이트
        return "redirect:/api/userStatus";
    }
}
