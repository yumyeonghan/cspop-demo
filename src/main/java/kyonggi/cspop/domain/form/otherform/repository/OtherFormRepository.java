package kyonggi.cspop.domain.form.otherform.repository;

import kyonggi.cspop.domain.form.otherform.OtherForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherFormRepository extends JpaRepository<OtherForm, Long> {
}
