package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.ExcelBoard;
import kyonggi.cspop.domain.board.dto.ExcelBoardResponseDto;
import kyonggi.cspop.domain.board.repository.ExcelBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExcelBoardService {

    private final ExcelBoardRepository excelBoardRepository;

    public List<ExcelBoard> findExcelList() {
        return excelBoardRepository.findAll();
    }

    public Page<ExcelBoardResponseDto> findAllExcelBoard(Pageable pageable) {
        return excelBoardRepository.findAllByOrderById(pageable).map(ExcelBoardResponseDto::new);
    }

    @Transactional
    public void deleteExcelListAndUploadExcelList(List<ExcelBoard> dataList) {
        excelBoardRepository.deleteAllInBatch();
        excelBoardRepository.saveAll(dataList);
    }

    public Optional<ExcelBoard> findExcelByStudentId(String studentId) {
        Optional<ExcelBoard> excelBoard = excelBoardRepository.findByStudentId(studentId);
        return excelBoard;
    }
}
