package com.weavus.board.repo;

import com.weavus.board.entity.Board;
import com.weavus.board.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board,Integer> {


    @Query(value ="select * from board where writter = ? ",nativeQuery = true)
    List<Board> findByWritter(String writter);
}
