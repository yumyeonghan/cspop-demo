package kyonggi.cspop.application.guide;

import kyonggi.cspop.application.guide.dto.GuidanceBoardRequestDto;
import kyonggi.cspop.application.guide.dto.GuidanceViewDto;
import kyonggi.cspop.domain.board.GuidanceBoard;
import kyonggi.cspop.domain.board.service.GuidanceBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/graduation")
public class GuidanceController {

    private final GuidanceBoardService guidanceBoardService;

    @GetMapping("/guide")
    public String guide(Model model) {
        GuidanceBoard guidanceBoard = guidanceBoardService.findGuidanceId(1l);
        model.addAttribute("data", guidanceBoard);
        return "graduation/guide";
    }

    @GetMapping("/modifyGuide/{guidanceBoardId}")
    public String guideModifyForm(@PathVariable Long guidanceBoardId, Model model) {
        GuidanceBoard guidanceBoard = guidanceBoardService.findGuidanceId(guidanceBoardId);
        model.addAttribute("data", new GuidanceViewDto(guidanceBoard));
        return "graduation/guidanceModify";
    }

    @PostMapping("/modifyGuide/{guidanceBoardId}")
    public String guideModify(@PathVariable Long guidanceBoardId, @Validated @ModelAttribute GuidanceBoardRequestDto guidanceBoardRequestDto, BindingResult result) {
        if (result.hasErrors()) {
            return "graduation/guidanceModify";
        }
        guidanceBoardService.updateGuidanceBoard(guidanceBoardId, guidanceBoardRequestDto);
        return "redirect:../guide";
    }
}
