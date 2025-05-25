package com.kh.reactbackend.service;
import com.kh.reactbackend.dto.BoardDto;

import java.util.List;
public interface BoardService {

    List<BoardDto.Response> findAllBoards();
    void createBoard(BoardDto.Create createDto);
    BoardDto.Response findBoardById(Long boardNo);
    void deleteBoard(Long boardNo);
    BoardDto.Response updateBoard(Long boardNo, BoardDto.Update updateDto);
}
