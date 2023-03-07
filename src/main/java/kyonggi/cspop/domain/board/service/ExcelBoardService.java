package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.repository.ExcelBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcelBoardService {

    private final ExcelBoardRepository excelBoardRepository;

    public List<ExcelBoard> findExcelList() {
        return excelBoardRepository.findAll();
    }

    @Transactional
    public void deleteExcelListAndUploadExcelList() {
        excelBoardRepository.deleteAllInBatch();

    }

}
