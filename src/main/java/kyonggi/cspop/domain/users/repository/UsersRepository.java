package kyonggi.cspop.domain.users.repository;


import kyonggi.cspop.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {

    // 회원가입, 학번으로 중복 체크
    boolean existsByStudentId(String studentId);
}
