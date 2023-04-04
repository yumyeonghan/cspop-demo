package kyonggi.cspop.domain.form.finalform.service;

import kyonggi.cspop.application.controller.form.finalForm.FinalFormDto;
import kyonggi.cspop.domain.form.finalform.FinalForm;
import kyonggi.cspop.domain.form.finalform.repository.FinalFormRepository;
import kyonggi.cspop.domain.uploadfile.FinalFormUploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FinalFormService {

    private final FinalFormRepository finalFormRepository;

    public FinalForm findFinalForm(Long id) {
        return finalFormRepository.findById(id).get();
    }

    @Transactional
    public Long saveFinalForm(FinalForm finalForm) {
        FinalForm saveForm = finalFormRepository.save(finalForm);
        return saveForm.getId();
    }

    @Transactional
    public void updateUserFinalForm(Long id, FinalFormDto finalFormDto, FinalFormUploadFile finalFormUploadFile) {
        FinalForm finalForm = finalFormRepository.findById(id).get();
        finalForm.updateFinalForm(finalFormDto);

        if (finalFormDto.getFinalFormUploadFile() != null) {
            finalForm.updateFiles(finalFormUploadFile);
        }
    }
}
