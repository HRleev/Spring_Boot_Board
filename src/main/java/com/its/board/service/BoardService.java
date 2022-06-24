package com.its.board.service;

import com.its.board.dto.BoardDTO;
import com.its.board.entity.BoardEntity;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity=BoardEntity.toSaveEntity(boardDTO);
    }
}
