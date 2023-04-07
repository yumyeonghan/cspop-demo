package kyonggi.cspop.application.controller.form.finalForm;


import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserDetailDto;
import kyonggi.cspop.application.controller.form.submitform.SubmitFormDto;
import kyonggi.cspop.application.util.FileStore;
import kyonggi.cspop.domain.board.excel.ExcelBoard;
import kyonggi.cspop.domain.board.excel.service.ExcelBoardService;
import kyonggi.cspop.domain.form.finalform.FinalForm;
import kyonggi.cspop.domain.form.finalform.service.FinalFormService;
import kyonggi.cspop.domain.form.submitform.SubmitForm;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.uploadfile.FinalFormUploadFile;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
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
public class FinalFormController {

    private final UsersService usersService;
    private final FinalFormService finalFormService;
    private final ExcelBoardService excelBoardService;

    private final FileStore fileStore;

    @GetMapping("api/finalForm")
    public String saveFinalForm(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, Model model) {
        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());
        Optional<ExcelBoard> excelByStudentId = excelBoardService.findExcelByStudentId(user.getStudentId());
        String advisor = excelByStudentId.get().getProfessorName();
        UserDetailDto userDetailDto = new UserDetailDto(user.getStudentId(), excelByStudentId.get().getGraduationDate(), user.getStudentName(), user.getDepartment(), excelByStudentId.get().getProfessorName(), user.getSubmitForm(),excelByStudentId.get().getCapstoneCompletion().equals("이수") ? true : false);

        model.addAttribute("userDetail", userDetailDto);
        return "graduation/form/finalForm";
    }

    @PostMapping("api/finalForm")
    public String saveFinalFormProgress(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, @Validated @ModelAttribute FinalFormDto finalFormDto) throws IOException {

        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());

        //최종 보고서 파일 저장
        FinalFormUploadFile finalFormUploadFile = fileStore.storeFinalFile(finalFormDto.getFinalFormUploadFile());

        //최종 보고서 폼 등록
        FinalForm finalForm = FinalForm.createFinalForm(finalFormDto.getTitle(), finalFormDto.getDivision(), finalFormDto.getQualification(), finalFormDto.getPageNumber(), finalFormUploadFile);
        Long finalFormId = finalFormService.saveFinalForm(finalForm);

        //유저 테이블 수정
        usersService.updateUserByFinalForm(user.getId(), finalFormId);

        //엑셀보드 업데이트
        excelBoardService.updateExcelByFinalForm(user);

        return "redirect:/api/userStatus";
    }
}
