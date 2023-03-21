package kyonggi.cspop.domain.users.service;

import kyonggi.cspop.application.users.dto.UserPasswordRequestDto;
import kyonggi.cspop.application.util.crypto.BCryptoPasswordEncoder;
import kyonggi.cspop.application.util.crypto.PasswordEncoder;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.repository.UsersRepository;
import kyonggi.cspop.exception.CsPopErrorCode;
import kyonggi.cspop.exception.CsPopException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UsersService implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public Long saveUser(Users user) {
        encryptPassword(user);
        Users saveUser = usersRepository.save(user);
        return saveUser.getId();
    }

    private void encryptPassword(Users user) {
        String encryptPassword = passwordEncoder.encryptPassword(user.getStudentPassword());
        user.encryptPassword(encryptPassword);
    }

    public void checkDuplicateStudentNumber(String studentId) {
        if (usersRepository.existsByStudentId(studentId)) {
            throw new CsPopException(CsPopErrorCode.DUPLICATE_STUDENT_ID);
        }
    }

    //사용 안함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /**
     * 비밀번호 변경 시 사용 메서드
     * @param studentId
     */

    //없는 학번이면 예외를 던짐
    public void checkExistStudentNumber(String studentId) {
        if (!usersRepository.existsByStudentId(studentId)) {
            throw new CsPopException(CsPopErrorCode.USER_NOT_FOUND);
        }
        log.info("존재하는 아이디 = {}", studentId);
    }

    //저장되어있는 비밀번호 대답이 아니면 예외를 던짐
    public void checkExistPasswordAnswer(String answerPw){
        if (!usersRepository.existsUsersByAnswerPw(answerPw)){
            throw new CsPopException(CsPopErrorCode.ANSWER_PASSWORD_NOT_FOUND);
        }
        log.info("비밀번호 답 = {}", answerPw);
    }


    //비밀번호 재설정
    @Transactional
    public void updatePassword(Long id,UserPasswordRequestDto userPasswordRequestDto) {
        Users users=usersRepository.findById(id).get();
        BCryptoPasswordEncoder encoder = new BCryptoPasswordEncoder();
        String securePw = encoder.encryptPassword(userPasswordRequestDto.getStudentPassword());
        users.updatePassword(securePw);
    }
}