package kyonggi.cspop.application.board.noticeBoard.dto;

import kyonggi.cspop.domain.board.NoticeBoard;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NoticeBoardRequestDto {

    private String title;
    private String text;
    private List<String> uploadFileName;
    private MultipartFile[] files;

    public NoticeBoard toEntity() {
        return NoticeBoard.createNoticeBoard();
    }
}
