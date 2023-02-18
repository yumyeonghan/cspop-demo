package kyonggi.cspop.domain.entity;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @Column(updatable = false)
    @Comment("등록일")
    private LocalDateTime createdDate;

    @Comment("수정일")
    private LocalDateTime lastModifiedDate;

    @Comment("수정자")
    private String modifier;

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdDate = now;
        lastModifiedDate = now;
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDate = LocalDateTime.now();
    }
}
