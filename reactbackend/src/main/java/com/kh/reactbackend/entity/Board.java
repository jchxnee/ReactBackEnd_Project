package com.kh.reactbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.w3c.dom.Text;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Board {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOARD_NO")
    private Long boardNo;

    @Column(name = "BOARD_TITLE", length = 50, nullable = false)
    private String boardTitle;

    @Column(name = "BOARD_INGREDIENT", nullable = false, columnDefinition = "TEXT")
    private String boardIngredient;

    @Column(name = "BOARD_RECIPE", nullable = false, columnDefinition = "TEXT")
    private String boardRecipe;

    @Column(name = "BOARD_THUMBNAIL")
    private String boardThumbnail;

    @Column(name = "BOARD_YOUTUBE")
    private String boardYoutube;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_WRITER", nullable = false)
    private Member member;


}
