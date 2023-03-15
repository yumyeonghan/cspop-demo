package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.board.dto.NoticeBoardResponseDto;
import kyonggi.cspop.domain.board.repository.NoticeBoardRepository;
import kyonggi.cspop.domain.users.Users;
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

    public Page<NoticeBoardResponseDto> findAllNoticeBoard(Pageable pageable) {
        return noticeBoardRepository.findAllByOrderByIdDesc(pageable).map(e-> new NoticeBoardResponseDto(e));
    }

    @Transactional
    public Long saveNoticeBoard(NoticeBoard noticeBoard) {
        NoticeBoard saveNoticeBoard = noticeBoardRepository.save(noticeBoard);
        return saveNoticeBoard.getId();
    }
}
