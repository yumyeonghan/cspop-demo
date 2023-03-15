package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.dto.NoticeBoardDto;
import kyonggi.cspop.domain.board.repository.NoticeBoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    public Page<NoticeBoardDto> findAllNoticeBoard(Pageable pageable) {
        return noticeBoardRepository.findAllByOrderByIdDesc(pageable).map(e-> new NoticeBoardDto(e));
    }

}
