package kyonggi.cspop.domain.login;

import kyonggi.cspop.application.util.crypto.PasswordEncoder;
import kyonggi.cspop.domain.login.dto.UserSessionDto;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.repository.UsersRepository;
import kyonggi.cspop.exception.CsPopErrorCode;
import kyonggi.cspop.exception.CsPopException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {
    private final PasswordEncoder passwordEncoder;
    private final UsersRepository usersRepository;

    //로그인
    public UserSessionDto login(String loginId, String loginPassword) {
        Users findUser = usersRepository.findByStudentId(loginId)
                .orElseThrow(() -> new CsPopException(CsPopErrorCode.USER_NOT_FOUND));
        isCorrectStudentPassword(loginPassword, findUser);
        return new UserSessionDto(findUser);
    }

    private void isCorrectStudentPassword(String loginPassword, Users findUser) {
        if (!passwordEncoder.isMatch(loginPassword, findUser.getStudentPassword())) {
            throw new CsPopException(CsPopErrorCode.WRONG_PASSWORD);
        }
    }
}
