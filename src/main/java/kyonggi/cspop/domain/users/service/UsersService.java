package kyonggi.cspop.domain.users.service;

import kyonggi.cspop.application.controller.users.dto.UserPasswordRequestDto;
import kyonggi.cspop.application.util.crypto.BCryptoPasswordEncoder;
import kyonggi.cspop.application.util.crypto.PasswordEncoder;
import kyonggi.cspop.domain.form.submitform.SubmitForm;
import kyonggi.cspop.domain.users.Users;
import kyonggi.cspop.domain.users.repository.UsersRepository;
import kyonggi.cspop.exception.CsPopErrorCode;
import kyonggi.cspop.exception.CsPopException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
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

    public Users findUserByStudentId(String studentId) {
        Users users = usersRepository.findByStudentId(studentId).get();
        if (!Objects.isNull(users.getSubmitForm())) {
            users.getSubmitForm().getStudentId();
        }
        return users;
    }

    //사용 안함
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    /**
     * 비밀번호 변경 시 사용 메서드
     *
     * @param studentId
     */

    //없는 학번이면 예외를 던짐
    public void checkExistStudentNumber(String studentId) {
        if (!usersRepository.existsByStudentId(studentId)) {
            throw new CsPopException(CsPopErrorCode.USER_NOT_FOUND);
        }
    }

    //저장되어있는 비밀번호 대답이 아니면 예외를 던짐
    public void checkExistPasswordAnswer(String answerPw) {
        if (!usersRepository.existsUsersByAnswerPw(answerPw)) {
            throw new CsPopException(CsPopErrorCode.ANSWER_PASSWORD_NOT_FOUND);
        }
    }

    //비밀번호 재설정
    @Transactional
    public void updatePassword(String studentId, UserPasswordRequestDto userPasswordRequestDto) {

        Users users = usersRepository.findByStudentId(studentId).get();

        //비밀번호 암호화
        BCryptoPasswordEncoder encoder = new BCryptoPasswordEncoder();
        String securePw = encoder.encryptPassword(userPasswordRequestDto.getStudentPassword());
        users.updatePassword(securePw);
    }

    @Transactional
    public void updateExcelBySubmitForm(Users users, SubmitForm submitForm) {
        users.addSubmitForms(submitForm);
        usersRepository.save(users);
    }
}