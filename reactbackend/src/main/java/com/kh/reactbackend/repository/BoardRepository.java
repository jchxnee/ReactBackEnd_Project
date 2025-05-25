package com.kh.reactbackend.repository;
import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Member;

import java.util.List;

public interface BoardRepository {
    List<Board> findAllBoards();
    void save(Board board);
    Board findById(Long boardNo);
    void delete (Board board);

}
