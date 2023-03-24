package kyonggi.cspop.domain.admins.service;


import kyonggi.cspop.domain.admins.repository.AdminsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminsService {

    private AdminsRepository adminsRepository;

}

