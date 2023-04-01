package kyonggi.cspop.application.controller.form.submitform;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.domain.board.service.ExcelBoardService;
import kyonggi.cspop.domain.board.service.form.SubmitFormService;
import kyonggi.cspop.domain.form.submitform.SubmitForm;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SubmitFormController {

    private final UsersService usersService;
    private final SubmitFormService submitFormService;

    private final ExcelBoardService excelBoardService;

    @PostMapping("api/submitForm")
    public String saveSubmitFormProgress(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, @Validated @ModelAttribute SubmitFormDto submitFormDto) {

        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());
        //수정폼 등록
        SubmitForm submitForm = SubmitForm.createSubmitForm(submitFormDto.getStudentId(), submitFormDto.getStudentName(), submitFormDto.getDepartment(), false, submitFormDto.getQualification());
        submitFormService.saveSubmitForm(submitForm);

        //유저 테이블 수정
        usersService.updateExcelBySubmitForm(user,submitForm);

        //엑셀보드에 유저 로우 저장
        excelBoardService.addExcelBySubmitForm(user, submitForm);

        log.info("폼 = {}", submitFormDto);
        //신청 폼 저장 -> 액셀 업데이트 -> 졸업 진행 상황 테이블 업데이트 -> 신청자 리스트 업데이트
        return "redirect:/api/userStatus";
    }
}
