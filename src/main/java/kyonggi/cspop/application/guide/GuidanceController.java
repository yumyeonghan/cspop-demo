package kyonggi.cspop.application.guide;

import kyonggi.cspop.application.guide.dto.GuidanceBoardDto;
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

        List<GuidanceBoard> guideList = guidanceBoardService.findGuideList();
        model.addAttribute("dataL", guideList);
        return "graduation/guide";
    }

    @GetMapping("/modify_guide/{id}")
    public String updateGuideForm(@PathVariable Long id, Model model) {
        save(id, model);
        return "graduation/modify_guide";
    }

    @PostMapping("/modify_guide/{id}")
    public String updateGuide(@PathVariable Long id,
                                 @Validated @ModelAttribute GuidanceBoardDto guidanceBoardDto,
                                 BindingResult result, Model model) {

        if (result.hasErrors()) {

            save(id, model);
            return "graduation/modify_guide";
        }
        //데이터 수정(update)
        guidanceBoardService.update(id, guidanceBoardDto);
        return "redirect:../guide";
    }

    public void save(@PathVariable Long id, Model model) {
        GuidanceBoard guidanceBoard = guidanceBoardService.findById(id);
        guidanceBoardService.save(guidanceBoard);
        model.addAttribute("data", guidanceBoard);
    }
}
