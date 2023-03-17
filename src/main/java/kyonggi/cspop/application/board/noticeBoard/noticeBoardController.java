package kyonggi.cspop.application.board.noticeBoard;

import kyonggi.cspop.application.SessionFactory;
import kyonggi.cspop.application.board.noticeBoard.dto.NoticeBoardRequestDto;
import kyonggi.cspop.application.board.noticeBoard.dto.NoticeViewDto;
import kyonggi.cspop.config.FileStore;
import kyonggi.cspop.domain.admins.Admins;
import kyonggi.cspop.domain.admins.repository.AdminsRepository;
import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.board.dto.NoticeBoardResponseDto;
import kyonggi.cspop.domain.board.service.NoticeBoardService;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.uploadfile.NoticeBoardUploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@Slf4j
public class noticeBoardController {

    @Value("${file.dir}")
    private String fileDir;

    private final NoticeBoardService noticeBoardService;
    private final AdminsRepository adminsRepository;
    private final FileStore fileStore;

    /**
     * page = 누를 페이지 번호 (1~n)
     * size = 한 페이지에 가져올 리스트 수 (10개 고정)
     * 요청 예시) Get 방식, /notice?page=0&size=10 요청
     */
    @GetMapping("/notice/find")
    public String findAllNoticeBoard(Pageable pageable, Model model) {
        Page<NoticeBoardResponseDto> allNoticeBoard = noticeBoardService.findAllNoticeBoard(pageable);

        int pageNumber = allNoticeBoard.getPageable().getPageNumber(); //현재페이지
        int totalPages = allNoticeBoard.getTotalPages(); //총 페이지 수
        int pageBlock = 10;
        int startBlockPage = ((pageNumber) / pageBlock) * pageBlock + 1; //현재 페이지가 7이라면 0*10+1=1
        int endBlockPage = startBlockPage + pageBlock - 1;
        endBlockPage = totalPages < endBlockPage ? totalPages : endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("allNoticeBoard", allNoticeBoard);

        return "graduation/notice";
    }

    //url 호출전 반드시 관리자로 로그인 한 상태에서 해야 세션에서 값을 가져와 DB에 저장하므로 주의해주세요
    @PostMapping("api/graduation/form")
    public String saveNoticeBoard(HttpServletRequest request, @ModelAttribute NoticeBoardRequestDto noticeBoardRequestDto) throws IOException {
        UserSessionDto adminSession = (UserSessionDto) request.getSession().getAttribute(SessionFactory.CSPOP_SESSION_KEY);
        Admins findAdmin = adminsRepository.findByAdminId(adminSession.getStudentId()).get();

        List<NoticeBoardUploadFile> storeFiles = fileStore.storeFiles(noticeBoardRequestDto.getFiles());

        //데이터베이스에 저장
        NoticeBoard noticeBoard = NoticeBoard.createNoticeBoard(noticeBoardRequestDto.getTitle(), noticeBoardRequestDto.getText(), false, 0, findAdmin, storeFiles);
        noticeBoardService.saveNoticeBoard(noticeBoard, storeFiles);

        return "redirect:/notice/find?page=0&size=10";
    }

    @ResponseBody
    @GetMapping("/attach/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{noticeBoardId}/{uploadFileName}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long noticeBoardId, @PathVariable String uploadFileName) throws MalformedURLException {
        NoticeBoard noticeBoard = noticeBoardService.findNoticeBoard(noticeBoardId);
        List<NoticeBoardUploadFile> uploadFiles = noticeBoard.getUploadFiles();
        for (NoticeBoardUploadFile uploadFile : uploadFiles) {
            if (uploadFile.getUploadFileName().equals(uploadFileName)) {
                String storeFileName = uploadFile.getStoreFileName();
                String dbUploadFileName = uploadFile.getUploadFileName();

                UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
                String encodedUploadFileName = UriUtils.encode(dbUploadFileName, StandardCharsets.UTF_8);
                String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
            }
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/notice/view/detail/{noticeBoardId}")
    public String viewDetail(@PathVariable Long noticeBoardId, Model model) {
        //자세히 보기 누르면 view + 1 돼야함.
        NoticeBoard detailNoticeBoard = noticeBoardService.findDetailNoticeBoard(noticeBoardId);
        model.addAttribute("detailView", new NoticeViewDto(detailNoticeBoard));
        return "graduation/noticeDetail";
    }


    @GetMapping("api/graduation/modifyForm/{noticeBoardId}")
    public String noticeModify(@PathVariable Long noticeBoardId, Model model) {

        NoticeBoard findNoticeBoard = noticeBoardService.findNoticeBoard(noticeBoardId);
        model.addAttribute("detailView", new NoticeViewDto(findNoticeBoard));
        return "graduation/noticeModifyForm";
    }
}
