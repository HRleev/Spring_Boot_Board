package com.its.board;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

import org.assertj.core.api.Assertions.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class BoardTest {
    @Autowired
    private BoardService boardService;

    public BoardDTO newBoard(int i) {
        BoardDTO board = new BoardDTO("제목" + i, "작성자" + i, "비밀번호" + i, "내용" + i, 1 + i);
        return board;
    }

    @Test
    @DisplayName("데이터 저장")
    public void boardSave() {
        IntStream.rangeClosed(1, 20).forEach(i -> {
            boardService.save(newBoard(i));
        });
    }

    @Test
    @Transactional
    @DisplayName("저장테스트")
    @Rollback
    public void saveTest() {
        Long saveId = boardService.save(newBoard(1));
        BoardDTO boardDTO = boardService.findById(saveId);

        assertThat(boardDTO.getBoardContents()).isEqualTo(boardDTO.getBoardContents());
    }
}
