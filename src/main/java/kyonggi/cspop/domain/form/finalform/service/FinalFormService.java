package kyonggi.cspop.domain.form.finalform.service;

import kyonggi.cspop.domain.form.finalform.FinalForm;
import kyonggi.cspop.domain.form.finalform.repository.FinalFormRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FinalFormService {

    private final FinalFormRepository finalFormRepository;

    @Transactional
    public Long saveFinalForm(FinalForm finalForm) {
        FinalForm saveForm = finalFormRepository.save(finalForm);
        return saveForm.getId();
    }
}
