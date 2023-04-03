package kyonggi.cspop.application.controller.board.guide;

import kyonggi.cspop.application.controller.board.guide.dto.GuidanceBoardRequestDto;
import kyonggi.cspop.application.controller.board.guide.dto.GuidanceViewDto;
import kyonggi.cspop.domain.board.guidance.GuidanceBoard;
import kyonggi.cspop.domain.board.guidance.service.GuidanceBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/graduation")
public class GuidanceController {

    private final GuidanceBoardService guidanceBoardService;

    @GetMapping("/guide")
    public String guide(Model model) {
        GuidanceBoard guidanceBoard = guidanceBoardService.findGuidanceId(1l);
        model.addAttribute("data", guidanceBoard);
        return "graduation/guide/guide";
    }

    @GetMapping("/modifyGuide/{guidanceBoardId}")
    public String guideModifyForm(@PathVariable Long guidanceBoardId, Model model) {
        GuidanceBoard guidanceBoard = guidanceBoardService.findGuidanceId(guidanceBoardId);
        model.addAttribute("data", new GuidanceViewDto(guidanceBoard));
        return "graduation/guide/guidanceModify";
    }

    @PostMapping("/modifyGuide/{guidanceBoardId}")
    public String guideModify(@PathVariable Long guidanceBoardId, @Validated @ModelAttribute GuidanceBoardRequestDto guidanceBoardRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "graduation/guide/guidanceModify";
        }
        guidanceBoardService.updateGuidanceBoard(guidanceBoardId, guidanceBoardRequestDto);
        return "redirect:../guide";
    }
}
