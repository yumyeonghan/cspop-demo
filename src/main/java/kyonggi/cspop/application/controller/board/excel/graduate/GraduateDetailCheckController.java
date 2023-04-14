package kyonggi.cspop.application.controller.board.excel.graduate;

import kyonggi.cspop.domain.board.excel.dto.ExcelBoardResponseDto;
import kyonggi.cspop.domain.board.excel.service.ExcelBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("api/graduation")
public class GraduateDetailCheckController {

    private final ExcelBoardService excelBoardService;


    @GetMapping("/graduate_submitForm")
    public String graduateSubmitForm(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> applyGraduation = excelBoardService.findAllStep(pageable, "신청접수");

        int pageNumber = applyGraduation.getPageable().getPageNumber();
        int totalPages = applyGraduation.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = Math.min(totalPages, endBlockPage);

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", applyGraduation);
        return "graduation/graduator/step/submitFormStep_list";
    }

    @GetMapping("/graduate_proposalForm")
    public String graduateProposalForm(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> proposalFormStep = excelBoardService.findAllStep(pageable,"제안서");

        int pageNumber = proposalFormStep.getPageable().getPageNumber();
        int totalPages = proposalFormStep.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = Math.min(totalPages, endBlockPage);

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", proposalFormStep);
        return "graduation/graduator/step/proposalFormStep_list";
    }

    @GetMapping("/graduate_interimForm")
    public String graduateInterimForm(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> interimFormStep = excelBoardService.findAllStep(pageable,"중간보고서");

        int pageNumber = interimFormStep.getPageable().getPageNumber();
        int totalPages = interimFormStep.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = Math.min(totalPages, endBlockPage);

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", interimFormStep);
        return "graduation/graduator/step/interimFormStep_list";
    }

    @GetMapping("/graduate_finalForm")
    public String graduateFinalForm(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> finalFormStep = excelBoardService.findAllStep(pageable,"최종보고서");

        int pageNumber = finalFormStep.getPageable().getPageNumber();
        int totalPages = finalFormStep.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = Math.min(totalPages, endBlockPage);

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", finalFormStep);
        return "graduation/graduator/step/finalFormStep_list";
    }

    @GetMapping("/graduate_otherForm")
    public String graduateOtherForm(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> otherFormStep = excelBoardService.findAllStep(pageable,"기타자격");

        int pageNumber = otherFormStep.getPageable().getPageNumber();
        int totalPages = otherFormStep.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = Math.min(totalPages, endBlockPage);

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", otherFormStep);
        return "graduation/graduator/step/otherFormStep_list";
    }

    @GetMapping("/graduate_finalPass")
    public String graduateFinalPass(Pageable pageable, Model model) {
        Page<ExcelBoardResponseDto> finalPassStep = excelBoardService.findAllStep(pageable,"최종통과");

        int pageNumber = finalPassStep.getPageable().getPageNumber();
        int totalPages = finalPassStep.getTotalPages();
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1;
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = Math.min(totalPages, endBlockPage);

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("graduator", finalPassStep);
        return "graduation/graduator/step/finalPassStep_list";
    }
}
