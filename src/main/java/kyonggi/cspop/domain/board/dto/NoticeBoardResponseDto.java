package kyonggi.cspop.domain.board.dto;

import kyonggi.cspop.domain.board.NoticeBoard;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeBoardResponseDto {

    private Long id;
    private String title;
    private boolean fixed;
    private Integer views;
    private String writer;
    private LocalDateTime createdDate;

    public NoticeBoardResponseDto(NoticeBoard noticeBoard) {
        this.id = noticeBoard.getId();
        this.title = noticeBoard.getTitle();
        this.fixed = noticeBoard.isFixed();
        this.views = noticeBoard.getViews();
        //this.writer = noticeBoard.getAdmins().getAdminName(); 보류
        this.createdDate = noticeBoard.getCreatedDate();
    }
}
