package kyonggi.cspop.domain.form.interimform.service;

import kyonggi.cspop.domain.form.interimform.InterimForm;
import kyonggi.cspop.domain.form.interimform.repository.InterimFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterimFormService {

    private final InterimFormRepository interimFormRepository;

    @Transactional
    public Long saveInterimForm(InterimForm interimForm) {
        InterimForm saveForm = interimFormRepository.save(interimForm);
        return saveForm.getId();
    }
}
