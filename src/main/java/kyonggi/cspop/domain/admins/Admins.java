package kyonggi.cspop.domain.admins;

import kyonggi.cspop.domain.entity.BaseEntity;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
public class Admins extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("관리자 이름")
    @Column(nullable = false)
    private String adminName;

    @Comment("관리자 아이디")
    @Column(nullable = false)
    private String adminId;

    @Comment("관리자 비밀번호")
    @Column(nullable = false)
    private String adminPassword;
}