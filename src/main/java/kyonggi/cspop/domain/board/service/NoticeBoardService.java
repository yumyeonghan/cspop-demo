package kyonggi.cspop.domain.board.service;

import kyonggi.cspop.application.controller.board.notice.dto.NoticeBoardRequestDto;
import kyonggi.cspop.domain.board.NoticeBoard;
import kyonggi.cspop.domain.board.dto.NoticeBoardResponseDto;
import kyonggi.cspop.domain.board.repository.NoticeBoardRepository;
import kyonggi.cspop.domain.uploadfile.NoticeBoardUploadFile;
import kyonggi.cspop.exception.CsPopErrorCode;
import kyonggi.cspop.exception.CsPopException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeBoardService {

    private final NoticeBoardRepository noticeBoardRepository;

    public Page<NoticeBoardResponseDto> findAllNoticeBoard(Pageable pageable) {
        return noticeBoardRepository.findAllByOrderByFixedDescIdDesc(pageable).map(e-> new NoticeBoardResponseDto(e));
    }

    @Transactional
    public Long saveNoticeBoard(NoticeBoard noticeBoard, List<NoticeBoardUploadFile> storeFiles) {
        storeFiles.stream().forEach(e -> e.designateNoticeBoard(noticeBoard));
        NoticeBoard saveNoticeBoard = noticeBoardRepository.save(noticeBoard);
        return saveNoticeBoard.getId();
    }

    public NoticeBoard findNoticeBoard(Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).get();
        initNoticeBoard(noticeBoard);
        return noticeBoard;
    }

    @Transactional
    public NoticeBoard findDetailNoticeBoard(Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).get();
        noticeBoard.updateViews();
        initNoticeBoard(noticeBoard);
        return noticeBoard;
    }

    private static void initNoticeBoard(NoticeBoard noticeBoard) {
        List<NoticeBoardUploadFile> uploadFiles = noticeBoard.getUploadFiles();
        if (uploadFiles.size() != 0) {
            noticeBoard.getUploadFiles().get(0);
        }
    }

    @Transactional
    public void updateNoticeBoard(Long id, NoticeBoardRequestDto noticeBoardRequestDto, List<NoticeBoardUploadFile> storeFiles) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).get();
        noticeBoard.updateTitle(noticeBoardRequestDto.getTitle());
        noticeBoard.updateText(noticeBoardRequestDto.getText());

        if (noticeBoardRequestDto.getFiles() != null) {
            noticeBoard.updateUploadFiles(storeFiles);
        }
    }

    @Transactional
    public void fixAndClearNoticeBoard(Long id) {
        NoticeBoard noticeBoard = noticeBoardRepository.findById(id).get();
        if (noticeBoard.isFixed()) {
            noticeBoard.updateFixed(false);
        }
        else {
            noticeBoard.updateFixed(true);
        }
    }

    @Transactional
    public void deleteNoticeBoard(Long id) {
        findValidation(id);
        noticeBoardRepository.deleteById(id);
    }

    private void findValidation(Long id) {
        if (!noticeBoardRepository.existsById(id)) {
            throw new CsPopException(CsPopErrorCode.NOTICE_BOARD_NOT_FOUND);
        }
    }
}
