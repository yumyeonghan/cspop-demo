package kyonggi.cspop.domain.board;

import kyonggi.cspop.domain.entity.BaseEntity;
import org.hibernate.annotations.Comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GuidanceBoard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("안내 및 내규 본문")
    private String text;
}
