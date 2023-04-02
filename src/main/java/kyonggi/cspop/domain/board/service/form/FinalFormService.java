package kyonggi.cspop.domain.board.service.form;

import kyonggi.cspop.domain.board.repository.FinalFormRepository;
import kyonggi.cspop.domain.board.repository.FinalFormUploadFileRepository;
import kyonggi.cspop.domain.form.finalform.FinalForm;
import kyonggi.cspop.domain.uploadfile.FinalFormUploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FinalFormService {

    private final FinalFormRepository finalFormRepository;

    private final FinalFormUploadFileRepository finalFormUploadFileRepository;

    @Transactional
    public Long saveFinalForm(FinalForm finalForm) {
        FinalForm saveForm = finalFormRepository.save(finalForm);
        return saveForm.getId();
    }

    @Transactional
    public Long saveFinalFormUploadFile(FinalFormUploadFile finalFormUploadFile) {
        FinalFormUploadFile saveFile = finalFormUploadFileRepository.save(finalFormUploadFile);
        return saveFile.getId();
    }
}
