package kyonggi.cspop.application.controller.search;

import kyonggi.cspop.domain.board.notice.NoticeBoard;
import kyonggi.cspop.domain.board.notice.dto.NoticeBoardResponseDto;
import kyonggi.cspop.domain.board.notice.repository.NoticeBoardRepository;
import kyonggi.cspop.domain.board.notice.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class SearchController {

    private final NoticeBoardService noticeBoardService;
    private final NoticeBoardRepository noticeBoardRepository;

    @GetMapping("/notice/search")
    public String searchNotice(@RequestParam String word, Pageable pageable, Model model) {
        Page<NoticeBoardResponseDto> searchNotice = noticeBoardService.findSearchNotice(pageable, word);
        if (searchNotice.isEmpty()) {
            model.addAttribute("errorMessage", true);
            return "graduation/notice/notice";
        }


        int pageNumber = searchNotice.getPageable().getPageNumber(); //현재페이지
        int totalPages = searchNotice.getTotalPages(); //총 페이지 수
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1; //현재 페이지가 7이라면 0*10+1=1
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("allNoticeBoard", searchNotice);

        return "graduation/notice/notice";
    }
}
