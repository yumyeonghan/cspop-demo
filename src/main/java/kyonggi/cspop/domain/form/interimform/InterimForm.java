package kyonggi.cspop.domain.form.interimform;

import kyonggi.cspop.domain.entity.BaseEntity;
import kyonggi.cspop.domain.users.Users;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterimForm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "submitForm")
    private Users users;

    @Column
    private boolean approval;

    public void designateUsers(Users users) {
        this.users = users;
    }

}
