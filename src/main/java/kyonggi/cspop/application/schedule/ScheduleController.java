package kyonggi.cspop.application.schedule;

import kyonggi.cspop.application.schedule.dto.ScheduleBoardDto;
import kyonggi.cspop.application.schedule.dto.ScheduleDto;
import kyonggi.cspop.application.schedule.dto.scheduleBoarad.*;
import kyonggi.cspop.domain.board.ScheduleBoard;
import kyonggi.cspop.domain.board.service.ScheduleBoardService;
import kyonggi.cspop.domain.schedule.Schedules;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/graduation")
public class ScheduleController {

    private final ScheduleBoardService scheduleBoardService;

    @GetMapping("/schedule")
    public String showSchedule(Model model) {

        //테이블 데이터 출력
        List<Schedules> schedules = scheduleBoardService.findScheduleList();
        model.addAttribute("schedules", schedules);

        //각 테이블 컬럼에 해당하는 세부 내용 출력
        List<ScheduleBoard> schedulesTextList = scheduleBoardService.findScheduleBoardList();
        model.addAttribute("schedulesTextList", schedulesTextList);

        return "graduation/progress_schedule";
    }

    //AJAX 통신용 API 진행일정 텍스트
    @PostMapping("/scheduleBoard/modify/receivedText")
    public ResponseEntity<Void> modifyReceivedText(@RequestBody ReceivedText receivedText) {
        scheduleBoardService.updateReceivedText(receivedText.getReceivedText());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/scheduleBoard/modify/proposalText")
    public ResponseEntity<Void> modifyProposalText(@RequestBody ProposalText proposalText) {
        scheduleBoardService.updateProposalText(proposalText.getProposalText());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/scheduleBoard/modify/interimReportText")
    public ResponseEntity<Void> modifyInterimReportText(@RequestBody InterimReportText interimReportText) {
        scheduleBoardService.updateInterimReportText(interimReportText.getInterimReportText());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/scheduleBoard/modify/finalReportText")
    public ResponseEntity<Void> modifyFinalReportText(@RequestBody FinalReportText finalReportText) {
        scheduleBoardService.updateFinalReportText(finalReportText.getFinalReportText());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/scheduleBoard/modify/finalPassText")
    public ResponseEntity<Void> modifyFinalPassText(@RequestBody FinalPassText finalPassText) {
        scheduleBoardService.updateFinalPassText(finalPassText.getFinalPassText());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/scheduleBoard/modify/otherQualificationsText")
    public ResponseEntity<Void> modifyOtherQualificationsText(@RequestBody OtherQualificationsText otherQualificationsText) {
        scheduleBoardService.updateOtherQualificationsText(otherQualificationsText.getOtherQualificationsText());
        return ResponseEntity.noContent().build();
    }

    //AJAX 통신용 API 진행일정 테이블
    @PostMapping("/schedule/modify/{id}")
    public ResponseEntity<Void> scheduleModify(@PathVariable Long id, @RequestBody ScheduleDto scheduleDto) {
        scheduleBoardService.updateSchedules(id, scheduleDto);
        return ResponseEntity.noContent().build();
    }
}