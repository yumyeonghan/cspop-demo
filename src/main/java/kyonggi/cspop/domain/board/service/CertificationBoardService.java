package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.CertificationBoard;
import kyonggi.cspop.domain.board.dto.CertificationBoardResponseDto;
import kyonggi.cspop.domain.board.repository.CertificationBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CertificationBoardService {

    private final CertificationBoardRepository certificationBoardRepository;

    public List<CertificationBoard> findCertificationList() {
        return certificationBoardRepository.findAll();
    }

    public Page<CertificationBoardResponseDto> findAllCertificationBoard(Pageable pageable) {
        return certificationBoardRepository.findAllByOrderById(pageable).map(CertificationBoardResponseDto::new);
    }

    @Transactional
    public void deleteExcelListAndUploadExcelList(List<CertificationBoard> dataList) {
        certificationBoardRepository.deleteAllInBatch();
        certificationBoardRepository.saveAll(dataList);
    }

}
