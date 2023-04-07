package kyonggi.cspop.application.controller.form.otherform;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserDetailDto;
import kyonggi.cspop.application.util.FileStore;
import kyonggi.cspop.domain.board.excel.ExcelBoard;
import kyonggi.cspop.domain.board.excel.service.ExcelBoardService;
import kyonggi.cspop.domain.form.otherform.OtherForm;
import kyonggi.cspop.domain.form.otherform.service.OtherFormService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.uploadfile.OtherFormUploadFile;
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

import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OtherFormController {

    private final UsersService usersService;
    private final OtherFormService otherFormService;
    private final ExcelBoardService excelBoardService;

    private final FileStore fileStore;

    @GetMapping("api/otherForm")
    public String saveOtherForm(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, Model model) {
        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());
        Optional<ExcelBoard> excelByStudentId = excelBoardService.findExcelByStudentId(user.getStudentId());
        UserDetailDto userDetailDto = new UserDetailDto(user.getStudentId(), excelByStudentId.get().getGraduationDate(), user.getStudentName(), user.getDepartment(), excelByStudentId.get().getProfessorName(), user.getSubmitForm(), excelByStudentId.get().getCapstoneCompletion().equals("이수") ? true : false);

        model.addAttribute("userDetail", userDetailDto);
        return "graduation/form/otherForm";
    }

    @PostMapping("api/otherForm")
    public String saveOtherFormProgress(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, @Validated @ModelAttribute OtherFormDto otherFormDto) throws IOException {

        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());

        //중간 보고서 파일 저장
        OtherFormUploadFile otherFormUploadFile = fileStore.storeOtherFile(otherFormDto.getOtherFormUploadFile());

        //중간 보고서 폼 등록
        OtherForm otherForm = OtherForm.createOtherForm(otherFormDto.getTitle(), otherFormDto.getDivision(), otherFormDto.getText(), otherFormUploadFile);
        Long otherFormId = otherFormService.saveOtherForm(otherForm);

        //유저 테이블 수정
        usersService.updateUserByOtherForm(user.getId(), otherFormId);

        //엑셀보드 업데이트
        excelBoardService.updateExcelByOtherForm(user);
        return "redirect:/api/userStatus";
    }
}

