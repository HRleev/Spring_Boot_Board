package com.its.board.repository;

import com.its.board.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {

    //native sql:update board_table set boardHits=boardHits+1 where id=?
    //jpql(java persistence query language)
    //스프링데이타 jpa 에서 제공하는 네이티브 쿼리 사용 방법
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits=b.boardHits+1 where b.id=:id" )
    //Entity를 타고 감 , 별칭을 꼭 써야함 , 별칭으로 접근
    void boardHits(@Param("id") Long id);
}
