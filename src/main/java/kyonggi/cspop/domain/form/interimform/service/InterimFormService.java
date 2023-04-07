package kyonggi.cspop.domain.form.interimform.service;

import kyonggi.cspop.application.controller.form.interimForm.InterimFormDto;
import kyonggi.cspop.domain.form.interimform.InterimForm;
import kyonggi.cspop.domain.form.interimform.repository.InterimFormRepository;
import kyonggi.cspop.domain.uploadfile.InterimFormUploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterimFormService {

    private final InterimFormRepository interimFormRepository;

    public InterimForm findInterimForm(Long id) {
        return interimFormRepository.findById(id).get();
    }

    @Transactional
    public Long saveInterimForm(InterimForm interimForm) {
        InterimForm saveForm = interimFormRepository.save(interimForm);
        return saveForm.getId();
    }

    @Transactional
    public void updateUserInterimForm(Long id, InterimFormDto interimFormDto, InterimFormUploadFile file) {
        InterimForm interimForm = interimFormRepository.findById(id).get();
        interimForm.updateInterimForm(interimFormDto.getTitle(), interimFormDto.getDivision(), interimFormDto.getText(), interimFormDto.getPlan());

        if (interimFormDto.getInterimFormUploadFile() != null) {
            interimForm.updateFile(file);
        }
    }
}
