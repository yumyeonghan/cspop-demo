package kyonggi.cspop.application.schedule;

import kyonggi.cspop.domain.board.ScheduleBoard;
import kyonggi.cspop.domain.board.service.ScheduleBoardService;
import kyonggi.cspop.domain.schedule.Schedules;
import kyonggi.cspop.domain.schedule.dto.ScheduleBoardDto;
import kyonggi.cspop.domain.schedule.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 진행 일정 테이블 + 게시판 컨트롤러
 */
@Controller
@Transactional
@RequiredArgsConstructor
@RequestMapping("/api/graduation")
public class ScheduleController {

    private final ScheduleBoardService scheduleBoardService;

    /**
     * 초기 진행 일정 및 세부 내용 뷰
     * @param model
     * @return
     */
    @GetMapping("/progress_schedule")
    public String Schedule(Model model){

        //테이블 데이터 출력
        List<Schedules> dataList = scheduleBoardService.findScheduleList();
        model.addAttribute("dataL", dataList);

        //각 테이블 컬럼에 해당하는 세부 내용 출력
        List<ScheduleBoard> dataList2 = scheduleBoardService.findScheduleBoardList();
        model.addAttribute("dataL2", dataList2);

        return "graduation/progress_schedule";
    }

    //진행 일정 수정 view - id에 일치하는 data 출력
    @GetMapping("/modify_schedule/{id}")
    public String mod(@PathVariable Long id, Model model){
        Schedules schedules = scheduleBoardService.findById(id);
        scheduleBoardService.save(schedules);
        model.addAttribute("data",schedules);
        return "graduation/modify_schedule";
    }

    @PostMapping("/modify_schedule/{id}")
    public String postMod(@PathVariable Long id, ScheduleDto scheduleDto) {

        //데이터 수정(update)
        scheduleBoardService.update(id, scheduleDto);
        return "redirect:../progress_schedule";
    }

    //세부 내용 수정 view
    @GetMapping("/schedule_board/{id}")
    public String mod2(@PathVariable Long id, Model model) {
        ScheduleBoard scheduleBoard = scheduleBoardService.findById_board(id);
        scheduleBoardService.save_board(scheduleBoard);
        model.addAttribute("data", scheduleBoard);

        return "graduation/schedule_board";
    }

    //세부 내용 수정 method
    @PostMapping("/schedule_board/{id}")
    public String Modify_Content(@PathVariable Long id, ScheduleBoardDto scheduleBoardDto){

        //데이터 수정(update)
        scheduleBoardService.update_board(id, scheduleBoardDto);
        return "redirect:../progress_schedule";
    }
}