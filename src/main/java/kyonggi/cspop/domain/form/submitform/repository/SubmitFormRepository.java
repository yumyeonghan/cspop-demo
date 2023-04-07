package kyonggi.cspop.domain.form.submitform.repository;

import kyonggi.cspop.domain.form.submitform.SubmitForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitFormRepository extends JpaRepository<SubmitForm, Long> {
}
