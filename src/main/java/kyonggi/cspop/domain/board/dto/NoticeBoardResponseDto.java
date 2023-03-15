package kyonggi.cspop.domain.board.dto;

import kyonggi.cspop.domain.board.NoticeBoard;
import lombok.Data;

import java.time.LocalDate;

@Data
public class NoticeBoardResponseDto {

    private Long id;
    private String title;
    private boolean fixed;
    private Integer views;
    private String writer;
    private LocalDate createdDate;

    public NoticeBoardResponseDto(NoticeBoard noticeBoard) {
        this.id = noticeBoard.getId();
        this.title = noticeBoard.getTitle();
        this.fixed = noticeBoard.isFixed();
        this.views = noticeBoard.getViews();
        this.writer = noticeBoard.getAdmins().getAdminName();
        this.createdDate = LocalDate.from(noticeBoard.getCreatedDate());
    }
}
