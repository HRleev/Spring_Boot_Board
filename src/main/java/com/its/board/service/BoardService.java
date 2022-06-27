package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
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
}
