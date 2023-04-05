package kyonggi.cspop.application.controller.board.userstatus;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserDetailDto;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserScheduleDto;
import kyonggi.cspop.application.controller.form.finalForm.FinalFormDto;
import kyonggi.cspop.application.controller.form.finalForm.FinalViewDto;
import kyonggi.cspop.application.util.FileStore;
import kyonggi.cspop.domain.board.excel.ExcelBoard;
import kyonggi.cspop.domain.board.excel.service.ExcelBoardService;
import kyonggi.cspop.domain.form.finalform.FinalForm;
import kyonggi.cspop.domain.form.finalform.service.FinalFormService;
import kyonggi.cspop.domain.form.submitform.enums.GraduationRequirements;
import kyonggi.cspop.domain.form.submitform.service.SubmitFormService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.schedule.Schedules;
import kyonggi.cspop.domain.schedule.enums.Step;
import kyonggi.cspop.domain.schedule.service.ScheduleService;
import kyonggi.cspop.domain.uploadfile.FinalFormUploadFile;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/userStatus")
@Slf4j
public class UserStatusController {

    private final UsersService usersService;
    private final ExcelBoardService excelBoardService;
    private final ScheduleService scheduleService;

    private final SubmitFormService submitFormService;

    private final FinalFormService finalFormService;

    private final FileStore fileStore;

    @GetMapping
    public String userStatusHome(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, Model model) {

        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());
        Optional<ExcelBoard> excelByStudentId = excelBoardService.findExcelByStudentId(user.getStudentId());
        if (Objects.isNull(user.getSubmitForm()) && excelByStudentId.isEmpty()) {
            model.addAttribute("errorMessage", true);
            model.addAttribute("userDetail", new UserDetailDto(user.getStudentId(), user.getStudentName(), user.getDepartment()));
            return "graduation/userstatus/applyGraduation";
        }

        /**
         * 유저별 세부 정보 데이터 전송
         */
        String advisor = excelByStudentId.get().getProfessorName();
        UserDetailDto userDetailDto = new UserDetailDto(user.getStudentId(), user.getStudentName(), user.getDepartment(), advisor != null ? advisor : "없음", excelByStudentId.get().getCapstoneCompletion().equals("이수") ? true : false, user.getSubmitForm(), excelByStudentId.get().getGraduationDate());
        model.addAttribute("userDetail", userDetailDto);

        /**
         * 진행 상황 테이블 데이터 전송
         */
        List<UserScheduleDto> userSchedules = new ArrayList<>();
        List<Schedules> scheduleList = scheduleService.findScheduleList();
        for (Schedules schedules : scheduleList) {
            if (user.getSubmitForm().getGraduationRequirements().equals(GraduationRequirements.Other_Qualifications)) {
                if (schedules.getStep().equals(Step.INTERIM_REPORT) || schedules.getStep().equals(Step.FINAL_REPORT)) {
                    continue;
                }
            }
            if (user.getSubmitForm().getGraduationRequirements().equals(GraduationRequirements.THESIS)) {
                if (schedules.getStep().equals(Step.OTHER_QUALIFICATIONS)) {
                    continue;
                }
            }

            if (schedules.getStep().equals(Step.RECEIVED)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(), schedules.getStartDate(), schedules.getEndDate(), Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료", Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.PROPOSAL)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(), schedules.getStartDate(), schedules.getEndDate(), Objects.isNull(user.getProposalForm()) ? "미제출" : "완료", Objects.isNull(user.getProposalForm()) || !user.getProposalForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.INTERIM_REPORT)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(), schedules.getStartDate(), schedules.getEndDate(), Objects.isNull(user.getInterimForm()) ? "미제출" : "완료", Objects.isNull(user.getInterimForm()) || !user.getInterimForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.FINAL_REPORT)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(), schedules.getStartDate(), schedules.getEndDate(), Objects.isNull(user.getFinalForm()) ? "미제출" : "완료", Objects.isNull(user.getFinalForm()) || !user.getFinalForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.OTHER_QUALIFICATIONS)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(), schedules.getStartDate(), schedules.getEndDate(), Objects.isNull(user.getOtherForm()) ? "미제출" : "완료", Objects.isNull(user.getOtherForm()) || !user.getOtherForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }
        }
        model.addAttribute("userSchedules", userSchedules);

        //진행 단계들이 모두 승인 났으면 최종 통과는 true, 아니면 false
        model.addAttribute("finalPass", userSchedules.stream().allMatch(e -> e.getApprovalStatus().equals("승인")));

        //함씨가 말한 어디까지 승인이 됐는지 뷰에 알려주는 로직 작성
        List<String> notApprovalList = new ArrayList<>();
        userSchedules.stream().filter(e -> e.getApprovalStatus().equals("미승인")).forEach(e -> notApprovalList.add(e.getStep()));
        model.addAttribute("notApprovalList", notApprovalList);

        return "graduation/userstatus/userGraduationStatus";
    }


    //AJAX 통신용 API 최종보고서 확인 뷰
    @GetMapping("/modifyFinalForm")
    public String FinalForm(@RequestParam("finalFormId") Long finalFormId, Model model) {

        FinalForm finalForm = finalFormService.findFinalForm(finalFormId);
        model.addAttribute("finalForm", new FinalViewDto(finalForm));
        return "graduation/form/finalFormModal";
    }

    //수정 로직
    @PostMapping("/modifyFinalForm")
    public ResponseEntity<Void> modifyFinalForm(@RequestParam("finalFormId") Long finalFormId, @RequestBody FinalFormDto finalFormDto) throws IOException {

        FinalFormUploadFile finalFormUploadFile = fileStore.storeFinalFile(finalFormDto.getFinalFormUploadFile());
        finalFormService.updateUserFinalForm(finalFormId, finalFormDto, finalFormUploadFile);
        return ResponseEntity.noContent().build();
    }
}