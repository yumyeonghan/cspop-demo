package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.application.guide.dto.GuidanceBoardDto;
import kyonggi.cspop.domain.board.GuidanceBoard;
import kyonggi.cspop.domain.board.repository.GuidanceBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GuidanceBoardService {

    private final GuidanceBoardRepository guidanceBoardRepository;

    //Schedules 로직
    public List<GuidanceBoard> findGuideList() {
        return guidanceBoardRepository.findAll();
    }

    public GuidanceBoard findGuidanceId(Long id){
        return guidanceBoardRepository.findById(id).get();
    }

    @Transactional
    public void updateGuidance(Long id, GuidanceBoardDto guidanceBoardDto) {
        GuidanceBoard guidanceBoard = findGuidanceId(id);
        guidanceBoard.updateGuidance(guidanceBoardDto);
    }
}
