package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Board;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BoardRepositoryImpl implements BoardRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Board> findAllBoards() {
        String jpql = "SELECT b FROM Board b JOIN FETCH b.member";
        return em.createQuery(jpql, Board.class).getResultList();
    }

    @Override
    public void save(Board board) {
        em.persist(board);
    }

    @Override
    public Board findById(Long boardNo) {
        return em.find(Board.class, boardNo);
    }

    @Override
    public void delete(Board board) {
        em.remove(board);
    }


}
