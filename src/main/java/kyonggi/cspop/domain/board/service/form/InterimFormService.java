package kyonggi.cspop.domain.board.service.form;

import kyonggi.cspop.domain.board.repository.InterimFormRepository;
import kyonggi.cspop.domain.board.repository.InterimFormUploadFileRepository;
import kyonggi.cspop.domain.form.interimform.InterimForm;
import kyonggi.cspop.domain.uploadfile.InterimFormUploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InterimFormService {

    private final InterimFormRepository interimFormRepository;

    private final InterimFormUploadFileRepository interimFormUploadFileRepository;

    @Transactional
    public Long saveInterimForm(InterimForm interimForm) {
        InterimForm saveForm = interimFormRepository.save(interimForm);
        return saveForm.getId();
    }

    @Transactional
    public Long saveInterimFormUploadFile(InterimFormUploadFile interimFormUploadFile) {
        InterimFormUploadFile saveFile = interimFormUploadFileRepository.save(interimFormUploadFile);
        return saveFile.getId();
    }
}
