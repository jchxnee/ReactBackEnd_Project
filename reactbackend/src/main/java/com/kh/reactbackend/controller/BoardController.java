package com.kh.reactbackend.controller;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5175")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public ResponseEntity<List<BoardDto.Response>> getBoards() {
        List<BoardDto.Response> responseList = boardService.findAllBoards();
        return ResponseEntity.ok(responseList);
    }

    @PostMapping("/add")
    public ResponseEntity<?> createBoard(@RequestBody BoardDto.Create createDto) {
        boardService.createBoard(createDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardDto.Response> getBoardById(@PathVariable Long id) {
        System.out.println("요청 받은 board id: " + id);
        BoardDto.Response response = boardService.findBoardById(id);
        System.out.println("조회 결과: " + response);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoardDto.Response> updateBoard(
            @PathVariable("id") Long boardNo,
            @RequestBody BoardDto.Update updateBoard
    ) throws IOException {
        BoardDto.Response updated = boardService.updateBoard(boardNo, updateBoard);
        return ResponseEntity.ok(updated);
    }
}
