package kyonggi.cspop.domain.comments;

import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.entity.BaseEntity;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
public class Comments extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("댓글")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeBoard_id", foreignKey = @ForeignKey(name = "fk_comments_to_notice_board"))
    private NoticeBoard noticeBoard;
}
