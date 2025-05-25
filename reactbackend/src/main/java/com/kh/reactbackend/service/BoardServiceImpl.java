package com.kh.reactbackend.service;

import com.kh.reactbackend.dto.BoardDto;
import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Member;
import com.kh.reactbackend.repository.BoardRepository;
import com.kh.reactbackend.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {


    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<BoardDto.Response> findAllBoards() {
        List<Board> boards = boardRepository.findAllBoards();

        return boards.stream()
                .map(board -> {
                    BoardDto.Response dto = BoardDto.Response.toDto(board);

                    // 콤마로 구분된 재료 문자열을 리스트로 변환
                    dto.setIngredient(
                            Arrays.stream(board.getBoardIngredient().split(","))
                                    .map(String::trim)
                                    .collect(Collectors.toList())
                    );

                    // 줄바꿈으로 구분된 레시피 문자열을 리스트로 변환
                    dto.setRecipe(
                            Arrays.stream(board.getBoardRecipe().split("\\n"))
                                    .map(String::trim)
                                    .collect(Collectors.toList())
                    );

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createBoard(BoardDto.Create createDto) {
        System.out.println("boardWriter: " + createDto.getBoardWriter());
        Optional<Member> memberOpt = memberRepository.findByUserId(createDto.getBoardWriter());

        if (!memberOpt.isPresent()) {
            System.out.println("해당 작성자 ID로 회원이 존재하지 않습니다.");
            throw new IllegalArgumentException("작성자를 찾을 수 없습니다.");
        }

        Member member = memberRepository.findByUserId(createDto.getBoardWriter())
                .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수 없습니다."));

        // DTO -> Entity 변환
        Board board = createDto.toEntity(member);

        // 저장
        boardRepository.save(board);
    }

    @Override
    public BoardDto.Response findBoardById(Long boardNo) {
        Board board = boardRepository.findById(boardNo);

        if (board == null) {
            throw new NoSuchElementException("게시글을 찾을 수 없습니다. boardNo=" + boardNo);
        }

        return BoardDto.Response.toDto(board);
    }

    @Override
    public void deleteBoard(Long boardNo) {
        Board board = boardRepository.findById(boardNo);
        if (board == null) {
            throw new RuntimeException("게시글이 존재하지 않습니다. id=" + boardNo);
        }
        boardRepository.delete(board);
    }

    @Transactional
    @Override
    public BoardDto.Response updateBoard(Long boardNo, BoardDto.Update updateDto){
        Board board = boardRepository.findById(boardNo);

        System.out.println("[updateBoard] 요청된 게시글 ID: " + boardNo);
        if (board == null) {
            System.out.println("[updateBoard] 게시글 없음. ID: " + boardNo);
            throw new RuntimeException("게시글이 존재하지 않습니다. id=" + boardNo);
        }

        System.out.println("[updateBoard] 기존 제목: " + board.getBoardTitle());
        System.out.println("[updateBoard] 기존 재료: " + board.getBoardIngredient());
        System.out.println("[updateBoard] 기존 레시피: " + board.getBoardRecipe());

        System.out.println("[updateBoard] 전달받은 제목: " + updateDto.getBoardTitle());
        System.out.println("[updateBoard] 전달받은 재료: " + updateDto.getBoardIngredient());
        System.out.println("[updateBoard] 전달받은 레시피: " + updateDto.getBoardRecipe());
        System.out.println("[updateBoard] 전달받은 썸네일: " + updateDto.getBoardThumbnail());
        System.out.println("[updateBoard] 전달받은 유튜브: " + updateDto.getBoardYoutube());

        if (updateDto.getBoardTitle() != null) {
            board.setBoardTitle(updateDto.getBoardTitle());
        }
        if (updateDto.getBoardIngredient() != null) {
            System.out.println("[updateBoard] 기존 재료: " + board.getBoardIngredient());
            System.out.println("[updateBoard] 전달받은 재료: " + updateDto.getBoardIngredient());
            board.setBoardIngredient(updateDto.getBoardIngredient());
        }
        if (updateDto.getBoardRecipe() != null) {
            board.setBoardRecipe(updateDto.getBoardRecipe());
        }
        if (updateDto.getBoardThumbnail() != null) {
            board.setBoardThumbnail(updateDto.getBoardThumbnail());
        }
        if (updateDto.getBoardYoutube() != null) {
            board.setBoardYoutube(updateDto.getBoardYoutube());
        }

        System.out.println("[updateBoard] 변경 후 제목: " + board.getBoardTitle());
        System.out.println("[updateBoard] 변경 후 재료: " + board.getBoardIngredient());

        // 변경 후 다시 dto 변환해 리턴
        return BoardDto.Response.toDto(board);
    }




}

