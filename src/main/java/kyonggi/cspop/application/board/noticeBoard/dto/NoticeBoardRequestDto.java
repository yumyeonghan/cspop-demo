package kyonggi.cspop.application.board.noticeBoard.dto;

import kyonggi.cspop.domain.board.NoticeBoard;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NoticeBoardRequestDto {

    private String title;
    private String text;
    // private MultipartFile file;
    private List<MultipartFile> files;
}
