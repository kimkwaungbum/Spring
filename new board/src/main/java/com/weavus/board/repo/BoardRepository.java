package com.weavus.board.repo;

import com.weavus.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BoardRepository extends JpaRepository<Board,Integer> {


}
