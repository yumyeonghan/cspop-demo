package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.board.dto.NoticeBoardResponseDto;
import kyonggi.cspop.domain.board.repository.NoticeBoardRepository;
import kyonggi.cspop.domain.uploadfile.NoticeBoardUploadFile;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    public Page<NoticeBoardResponseDto> findAllNoticeBoard(Pageable pageable) {
        return noticeBoardRepository.findAllByOrderByIdDesc(pageable).map(e-> new NoticeBoardResponseDto(e));
    }

    @Transactional
    public Long saveNoticeBoard(NoticeBoard noticeBoard, List<NoticeBoardUploadFile> storeFiles) {
        storeFiles.stream().forEach(e -> e.designateNoticeBoard(noticeBoard));
        NoticeBoard saveNoticeBoard = noticeBoardRepository.save(noticeBoard);
        return saveNoticeBoard.getId();
    }

    public NoticeBoard findNoticeBoard(Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).get();
        noticeBoard.getUploadFiles().get(0); //배치 사이즈 만큼 호출, LazyInitializationException 해결
        return noticeBoard;
    }
}
