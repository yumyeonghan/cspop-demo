package kyonggi.cspop.application.board.noticeBoard;

import kyonggi.cspop.domain.board.dto.NoticeBoardDto;
import kyonggi.cspop.domain.board.service.NoticeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class noticeBoardController {

    private final NoticeBoardService noticeBoardService;

    /**
     * page = 누를 페이지 번호 (1~n)
     * size = 한 페이지에 가져올 리스트 수 (10개 고정)
     * 요청 예시) Get 방식, /notice?page=0&size=10 요청
     */
    @GetMapping("/notice")
    public Page<NoticeBoardDto> findAllNoticeBoard(Pageable pageable) {
        Page<NoticeBoardDto> allNoticeBoard = noticeBoardService.findAllNoticeBoard(pageable);
        return allNoticeBoard;
    }

}
