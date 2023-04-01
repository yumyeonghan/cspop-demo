package kyonggi.cspop.domain.board.service.form;

import kyonggi.cspop.domain.board.repository.ProposalFormRepository;
import kyonggi.cspop.domain.form.proposalform.ProposalForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProposalFormService {

    private final ProposalFormRepository proposalFormRepository;

    @Transactional
    public Long saveProposalForm(ProposalForm proposalForm) {
        ProposalForm saveForm = proposalFormRepository.save(proposalForm);
        return saveForm.getId();
    }
}
