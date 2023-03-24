package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.application.controller.board.guide.dto.GuidanceBoardRequestDto;
import kyonggi.cspop.domain.board.GuidanceBoard;
import kyonggi.cspop.domain.board.repository.GuidanceBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuidanceBoardService {

    private final GuidanceBoardRepository guidanceBoardRepository;

    public GuidanceBoard findGuidanceId(Long id){
        return guidanceBoardRepository.findById(id).get();
    }

    @Transactional
    public void updateGuidanceBoard(Long id, GuidanceBoardRequestDto guidanceBoardRequestDto) {
        GuidanceBoard guidanceBoard = guidanceBoardRepository.findById(id).get();
        guidanceBoard.updateGuidance(guidanceBoardRequestDto.getText());
    }
}
