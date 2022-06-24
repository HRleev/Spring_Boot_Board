package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import com.its.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public Long save(BoardDTO boardDTO) {
       Long savedId= boardRepository.save(BoardEntity.toSaveEntity(boardDTO)).getId();
        return savedId;
    }
}
