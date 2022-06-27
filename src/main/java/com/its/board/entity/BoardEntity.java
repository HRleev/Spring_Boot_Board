package com.its.board.entity;


import com.its.board.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    //상속을 받았기때문에 작성시간 수정시간을 모두 사용 할 수 있다


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "boardTitle",length = 50 ,nullable = false)
    //nullable = false -- notnull
    private String boardTitle;
    @Column(name = "boardWriter",length = 20)
    private String boardWriter;
    @Column(name = "boardPassword",length = 20)
    private String boardPassword;
    @Column(name = "boardContents",length = 500)
    private String boardContents;
    @Column(name = "boardHits")
    private int boardHits;
    @Column
    private String boardFileName;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setId(boardEntity.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0);

        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity=new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPassword(boardDTO.getBoardPassword());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(boardEntity.getBoardHits());
        return boardEntity;
    }
}
