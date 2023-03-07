package kyonggi.cspop.application.schedule;

import kyonggi.cspop.domain.board.ScheduleBoard;
import kyonggi.cspop.domain.board.repository.ScheduleBoardRepository;
import kyonggi.cspop.domain.board.repository.ScheduleRepository;
import kyonggi.cspop.domain.schedule.Schedules;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 진행 일정 테이블 + 게시판 컨트롤러
 */
@Controller
@RequiredArgsConstructor
@Transactional
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private final ScheduleRepository scheduleRepository;
    @Autowired
    private final ScheduleBoardRepository scheduleBoardRepository;

    /**
     * 초기 진행 일정 및 세부 내용 뷰
     * @param model
     * @return
     */
    @GetMapping("/graduation/progress_schedule")
    public String Schedule(Model model){

        /**
         * 테이블 데이터 출력
         */
        List<Schedules> dataList=scheduleRepository.findAll();

        for (Schedules schedules:dataList){
            schedules.getStep();
            schedules.getStartDate();
            schedules.getEndDate();
            schedules.getScheduleState();
        }
        model.addAttribute("dataL", dataList);

        /**
         * 각 테이블 컬럼에 해당하는 세부 내용 출력
         */
        List<ScheduleBoard> dataList2=scheduleBoardRepository.findAll();

        for (ScheduleBoard scheduleBoard:dataList2){
            scheduleBoard.getReceivedText();
            scheduleBoard.getProposalText();
            scheduleBoard.getInterimReportText();
            scheduleBoard.getFinalReportText();
            scheduleBoard.getFinalPassText();
            scheduleBoard.getOtherQualificationsText();
        }
        model.addAttribute("dataL2", dataList2);

        return "graduation/progress_schedule";
    }

    /**
     * 진행 일정 수정 method
     * @param model
     * @return
     */
    @PostMapping("/graduation/progress_schedule.modify")
    public String Modify(Model model){

        return "graduation/progress_schedule";
    }

    /**
     * 세부 내용 수정 method
     * @param model
     * @return
     */
    @PostMapping("/graduation/progress_schedule.modify2")
    public String Modify_Content(Model model){

        return "graduation/progress_schedule";
    }
}