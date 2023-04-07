package kyonggi.cspop.domain.form.interimform.repository;

import kyonggi.cspop.domain.form.interimform.InterimForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterimFormRepository extends JpaRepository<InterimForm, Long> {
}
