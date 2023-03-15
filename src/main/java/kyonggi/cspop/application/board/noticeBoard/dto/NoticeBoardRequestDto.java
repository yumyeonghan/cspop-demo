package kyonggi.cspop.application.board.noticeBoard.dto;

import kyonggi.cspop.domain.board.NoticeBoard;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class NoticeBoardRequestDto {

    private String title;
    private String text;
    private String uploadFileName;
    private MultipartFile file;

    public NoticeBoard toEntity() {
        return NoticeBoard.createNoticeBoard();
    }
}
