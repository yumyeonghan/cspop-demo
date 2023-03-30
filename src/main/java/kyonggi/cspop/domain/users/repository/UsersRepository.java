package kyonggi.cspop.domain.users.repository;


import kyonggi.cspop.domain.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    // 회원가입, 학번으로 중복 체크
    boolean existsByStudentId(String studentId);

    // 비밀번호 대답 체크
    boolean existsUsersByAnswerPw(String answerPw);

    @Query("select u from Users u join fetch u.submitForm Where u.studentId = :studentId")
    Optional<Users> findByStudentId(@Param("studentId") String studentId);
}
