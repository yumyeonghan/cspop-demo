package kyonggi.cspop.domain.admins.repository;

import kyonggi.cspop.domain.admins.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminsRepository extends JpaRepository<Admins, Long> {

    Optional<Admins> findByAdminId(String loginId);
}
