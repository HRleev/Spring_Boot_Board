package com.its.board.service;

import com.its.board.common.PagingConst;
import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDTO boardDTO) {
       Long savedId= boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();
        return savedId;
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity>boardEntityList=boardRepository.findAll();
        List<BoardDTO>boardDTOList=new ArrayList<>();
        for(BoardEntity board: boardEntityList){
            BoardDTO boardDTO=BoardDTO.toDTO(board);
            boardDTOList.add(boardDTO);
        }return  boardDTOList;
    }

    @Transactional
    public BoardDTO findById(Long id) {
        //조회수 처리
        //native sql:update board_table set boardHits=boardHits+1 where id=?

        boardRepository.boardHits(id);
        Optional<BoardEntity>optionalBoardEntity=boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity=optionalBoardEntity.get();
            return BoardDTO.toDTO(boardEntity);
//            return BoardDTO.toDTO(optionalBoardEntity.get());
        }else {
            return null;
        }
    }

    public void update(BoardDTO boardDTO) {
        BoardEntity boardEntity =BoardEntity.toUpdateEntity(boardDTO);
        boardRepository.save(boardEntity);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    public Page<BoardDTO> paging(Pageable pageable) {
        int page = pageable.getPageNumber();//요청페이지값을 가져옴
        // 요청한 페이지가 1이면 페이지값을 0으로 하고 1이 아니면 요청 페이지에서 1을 뺀다.
//        page = page - 1;
        //삼항 연산자
        page = (page == 1)? 0: (page-1);
        Page<BoardEntity> boardEntities = boardRepository.findAll(PageRequest.of(page, PagingConst.PAGE_LIMIT, Sort.by(Sort.Direction.DESC, "id")));
        // Page<BoardEntity> => Page<BoardDTO>
        Page<BoardDTO> boardList = boardEntities.map(
                //boardEntity객체->BoardDTO객체 변환
                //board:BoardEntity객체
                //new BoardDTO()생성자
                board -> new BoardDTO(board.getId(),
                        board.getBoardTitle(),
                        board.getBoardWriter(),
                        board.getBoardHits(),
                        board.getCreatedTime()
                ));
        return boardList;
    }
}
