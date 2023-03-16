package kyonggi.cspop.application.board.noticeBoard.dto;

import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.uploadfile.NoticeBoardUploadFile;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class NoticeViewDto {

    String title;
    String text;
    Integer views;
    LocalDate createdDate;
    List<String> files;

    public NoticeViewDto(NoticeBoard noticeBoard) {

        List<NoticeBoardUploadFile> uploadFiles = noticeBoard.getUploadFiles();
        List<String> files = null;
        if (uploadFiles.size() != 0) {
            files = uploadFiles.stream().map(e -> e.getUploadFileName()).collect(Collectors.toList());
        }
        this.title = noticeBoard.getTitle();
        this.text = noticeBoard.getText();
        this.views = noticeBoard.getViews();
        this.createdDate = LocalDate.from(noticeBoard.getCreatedDate());
        this.files = files;
    }
}
