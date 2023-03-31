package kyonggi.cspop.application.controller.form.submitform;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class SubmitFormController {

    @PostMapping("api/submitForm")
    public String saveSubmitForm(@ModelAttribute SubmitFormDto submitFormDto) {
        log.info("폼 = {}", submitFormDto);
        log.info("안녕하세요");
        //신청 폼 저장 -> 액셀 업데이트 -> 졸업 진행 상황 테이블 업데이트 -> 신청자 리스트 업데이트
        return "redirect:api/userStatus";
    }
}
