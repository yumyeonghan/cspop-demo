package kyonggi.cspop.domain.form.otherform.service;

import kyonggi.cspop.domain.form.otherform.OtherForm;
import kyonggi.cspop.domain.form.otherform.repository.OtherFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OtherFormService {

    private final OtherFormRepository otherFormRepository;

    @Transactional
    public Long saveOtherForm(OtherForm otherForm) {
        OtherForm saveForm = otherFormRepository.save(otherForm);
        return saveForm.getId();
    }
}
