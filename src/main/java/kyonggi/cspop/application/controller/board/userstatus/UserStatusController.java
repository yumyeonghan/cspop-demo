package kyonggi.cspop.application.controller.board.userstatus;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserDetailDto;
import kyonggi.cspop.application.controller.board.userstatus.dto.UserScheduleDto;
import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.service.ExcelBoardService;
import kyonggi.cspop.domain.board.service.ScheduleBoardService;
import kyonggi.cspop.domain.form.submitform.enums.GraduationRequirements;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.schedule.Schedules;
import kyonggi.cspop.domain.schedule.enums.Step;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

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
    private final ScheduleBoardService scheduleBoardService;

    @GetMapping
    public String userStatusHome(@SessionAttribute(name = SessionFactory.CSPOP_SESSION_KEY, required = false) UserSessionDto userSessionDto, Model model) {

        Users user = usersService.findUserByStudentId(userSessionDto.getStudentId());
        Optional<ExcelBoard> excelByStudentId = excelBoardService.findExcelByStudentId(user.getStudentId());
        if (Objects.isNull(user.getSubmitForm()) && excelByStudentId.isEmpty()) {
            model.addAttribute("errorMessage", true);
            model.addAttribute("userDetail",new UserDetailDto(user.getStudentId(), user.getStudentName(), user.getDepartment()));
            return "graduation/userstatus/applyGraduation";
        }

        /**
         * 유저별 세부 정보 데이터 전송
         */
        String advisor = excelByStudentId.get().getProfessorName();
        UserDetailDto userDetailDto = new UserDetailDto(user.getStudentId(), user.getStudentName(), user.getDepartment(), advisor != null ? advisor : "없음", excelByStudentId.get().getCapstoneCompletion().equals("이수") ? true : false, user.getSubmitForm());
        model.addAttribute("userDetail", userDetailDto);

        /**
         * 진행 상황 테이블 데이터 전송
         */
        List<UserScheduleDto> userSchedules = new ArrayList<>();
        List<Schedules> scheduleList = scheduleBoardService.findScheduleList();
        for (Schedules schedules : scheduleList) {
            if (user.getSubmitForm().getGraduationRequirements().equals(GraduationRequirements.Other_Qualifications)) {
                if (schedules.getStep().equals(Step.PROPOSAL) || schedules.getStep().equals(Step.INTERIM_REPORT) || schedules.getStep().equals(Step.FINAL_REPORT)) {
                    continue;
                }
            }
            if (user.getSubmitForm().getGraduationRequirements().equals(GraduationRequirements.THESIS)) {
                if (schedules.getStep().equals(Step.OTHER_QUALIFICATIONS)) {
                    continue;
                }
            }
            //졸업 신청 요건별로 들어가는 테이블 나눴고, 이제 그에 맞게 데이터 넣어주면 됨
            //신청서 별로 승인 신청 여부에 따라 데이터 넣어주면 되고,
            //데이터는 단계(스케쥴 단계 그대로), 일정(스케쥴 시작일정, 종료일정 그대로), 제출(제출했으면 완료, 안했으면 미제출 ), 비고(폼 양식마다 승인이면 승인, 아니면 미승인)
            //jsp 에서 테이블에서 미제출 누르면 해당 신청 폼으로 가지는데 이때 전단계가 승인인지 미승인인지 검증 후 이동 or x
            if (schedules.getStep().equals(Step.RECEIVED)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(),
                        schedules.getStartDate(),
                        schedules.getEndDate(),
                        Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료",
                        Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.PROPOSAL)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(),
                        schedules.getStartDate(),
                        schedules.getEndDate(),
                        Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료",
                        Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.INTERIM_REPORT)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(),
                        schedules.getStartDate(),
                        schedules.getEndDate(),
                        Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료",
                        Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.FINAL_REPORT)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(),
                        schedules.getStartDate(),
                        schedules.getEndDate(),
                        Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료",
                        Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.OTHER_QUALIFICATIONS)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(),
                        schedules.getStartDate(),
                        schedules.getEndDate(),
                        Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료",
                        Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }

            if (schedules.getStep().equals(Step.FINAL_PASS)) {
                UserScheduleDto userScheduleDto = new UserScheduleDto(schedules.getStep().getStepToString(),
                        schedules.getStartDate(),
                        schedules.getEndDate(),
                        Objects.isNull(user.getSubmitForm()) ? "미제출" : "완료",
                        Objects.isNull(user.getSubmitForm()) || !user.getSubmitForm().isApproval() ? "미승인" : "승인");
                userSchedules.add(userScheduleDto);
            }
        }
        log.info("userSchedules = {}", userSchedules);
        model.addAttribute("userSchedules", userSchedules);
        return "graduation/userstatus/userGraduationStatus";
    }
}