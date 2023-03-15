package kyonggi.cspop.application.board.noticeBoard;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.board.noticeBoard.dto.NoticeBoardRequestDto;
import kyonggi.cspop.domain.board.dto.NoticeBoardResponseDto;
import kyonggi.cspop.domain.board.service.NoticeBoardService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class noticeBoardController {

    @Value("${file.dir}")
    private String fileDir;

    private final NoticeBoardService noticeBoardService;

    /**
     * page = 누를 페이지 번호 (1~n)
     * size = 한 페이지에 가져올 리스트 수 (10개 고정)
     * 요청 예시) Get 방식, /notice?page=0&size=10 요청
     */
    @GetMapping("/notice/find")
    public String findAllNoticeBoard(Pageable pageable, Model model) {
        Page<NoticeBoardResponseDto> allNoticeBoard = noticeBoardService.findAllNoticeBoard(pageable);

        int pageNumber=allNoticeBoard.getPageable().getPageNumber(); //현재페이지
        int totalPages=allNoticeBoard.getTotalPages(); //총 페이지 수
        int pageBlock = 10;
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 0*10+1=1
        int endBlockPage = startBlockPage+pageBlock-1;
        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("allNoticeBoard", allNoticeBoard);

        return "graduation/notice";
    }

    //url 호출전 반드시 관리자로 로그인 한 상태에서 해야 세션에서 값을 가져와 DB에 저장하므로 주의해주세요
    @PostMapping("api/notice/form")
    public String saveNoticeBoard (HttpServletRequest request, @ModelAttribute NoticeBoardRequestDto noticeBoardRequestDto) throws IOException {
        UserSessionDto adminSession = (UserSessionDto) request.getSession().getAttribute(SessionFactory.CSPOP_SESSION_KEY);
        log.info(adminSession.getStudentName());
        if(!noticeBoardRequestDto.getFile().isEmpty()) {
            String fullPath = fileDir + noticeBoardRequestDto.getFile().getOriginalFilename();
            noticeBoardRequestDto.getFile().transferTo(new File(fullPath));
        }
        return "notice/noticeForm";
    }

}
