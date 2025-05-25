package com.kh.reactbackend.dto;

import com.kh.reactbackend.entity.Board;
import com.kh.reactbackend.entity.Member;
import lombok.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Setter
    public static class Create {
        private Long boardNo;
        private String boardTitle;
        private String boardWriter;
        private String boardRecipe;
        private String boardIngredient;
        private String boardThumbnail;
        private String boardYoutube;


        public Board toEntity(Member member) {
            return Board.builder()
                    .boardTitle(this.boardTitle)
                    .boardIngredient(this.boardIngredient)
                    .boardRecipe(this.boardRecipe)
                    .boardThumbnail(this.boardThumbnail)
                    .boardYoutube(this.boardYoutube)
                    .member(member) // 작성자 정보 포함
                    .build();
        }
    }
        @Getter
        @NoArgsConstructor
        @AllArgsConstructor
        @Setter
        public static class Update{
            private Long boardNo;
            private String boardTitle;
            private String boardWriter;
            private String boardRecipe;
            private String boardIngredient;
            private String boardThumbnail;
            private String boardYoutube;

            public Board toEntity(Member member) {
            return Board.builder()
                    .boardNo(this.boardNo)
                    .boardTitle(this.boardTitle)
                    .boardIngredient(this.boardIngredient)
                    .boardRecipe(this.boardRecipe)
                    .boardThumbnail(this.boardThumbnail)
                    .boardYoutube(this.boardYoutube)
                    .build();
        }


    }

    // 응답용 DTO (Response)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    public static class Response {
        private Long id;             // BOARD_NO
        private String userid;       // BOARD_WRITER (Member.userId)
        private String title;        // BOARD_TITLE
        private List<String> ingredient;  // BOARD_INGREDIENT
        private List<String> recipe;       // BOARD_RECIPE
        private String image;        // BOARD_THUMBNAIL
        private String youtubeId;    // BOARD_YOUTUBE

        public static Response toDto(Board board) {
            return Response.builder()
                    .id(board.getBoardNo())
                    .userid(board.getMember().getUserId())
                    .title(board.getBoardTitle())
                    .ingredient(Arrays.stream(board.getBoardIngredient().split(","))
                            .map(String::trim)
                            .collect(Collectors.toList()))
                    .recipe(Arrays.stream(board.getBoardRecipe().split("\\n"))
                            .map(String::trim)
                            .collect(Collectors.toList()))
                    .image(board.getBoardThumbnail())
                    .youtubeId(board.getBoardYoutube())
                    .build();
        }
    }

}
