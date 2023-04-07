package kyonggi.cspop.domain.form.proposalform.repository;

import kyonggi.cspop.domain.form.proposalform.ProposalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProposalFormRepository extends JpaRepository<ProposalForm, Long> {
}
