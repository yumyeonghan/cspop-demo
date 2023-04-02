package kyonggi.cspop.domain.board.repository;

import kyonggi.cspop.domain.form.finalform.FinalForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinalFormRepository extends JpaRepository<FinalForm, Long> {
}
