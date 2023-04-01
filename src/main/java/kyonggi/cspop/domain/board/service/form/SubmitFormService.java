package kyonggi.cspop.domain.board.service.form;

import kyonggi.cspop.application.controller.form.submitform.SubmitFormDto;
import kyonggi.cspop.domain.board.repository.SubmitFormRepository;
import kyonggi.cspop.domain.form.submitform.SubmitForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SubmitFormService {

    private final SubmitFormRepository submitFormRepository;

    @Transactional
    public Long saveSubmitForm(SubmitForm submitForm) {
        SubmitForm saveForm = submitFormRepository.save(submitForm);
        return saveForm.getId();
    }

    @Transactional
    public void updateSubmitForm(Long id, SubmitFormDto submitFormDto) {

    }
}
