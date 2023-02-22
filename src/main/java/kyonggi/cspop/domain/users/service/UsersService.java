package kyonggi.cspop.domain.users.service;

import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.repository.UsersRepository;
import kyonggi.cspop.exception.CsPopErrorCode;
import kyonggi.cspop.exception.CsPopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UsersService {

    private final UsersRepository usersRepository;

    @Transactional
    public Long saveUser(Users user) {
        Users saveUser = usersRepository.save(user);
        return saveUser.getId();
    }

    public void checkDuplicateStudentNumber(String studentId) {
        if (usersRepository.existsByStudentId(studentId)) {
            throw new CsPopException(CsPopErrorCode.DUPLICATE_STUDENT_ID);
        }
    }
}
