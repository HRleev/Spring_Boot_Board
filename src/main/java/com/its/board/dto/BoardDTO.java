package com.its.board.dto;

import com.its.board.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardTitle;
    private String boardPassword;
    private String boardContents;
    private int boardHits;


    public BoardDTO(String boardWriter, String boardTitle, String boardPassword, String boardContents, int boardHits) {
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardPassword = boardPassword;
        this.boardContents = boardContents;
        this.boardHits = boardHits;
    }

    public static BoardDTO boardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO=new BoardDTO();
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPassword(boardEntity.getBoardPassword());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        return boardDTO;
    }
}
