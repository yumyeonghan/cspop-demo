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

    public GuidanceBoard findById(Long id){
        return guidanceBoardRepository.findById(id).get();
    }

    @Transactional
    public void save(GuidanceBoard guidanceBoard) {
        guidanceBoardRepository.save(guidanceBoard);
    }

    @Transactional
    public void update(Long id, GuidanceBoardDto guidanceBoardDto) {
        GuidanceBoard guidanceBoard = findById(id);
        guidanceBoard.updateGuidance(guidanceBoardDto);
    }
}
