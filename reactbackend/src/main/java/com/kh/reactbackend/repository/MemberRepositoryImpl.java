package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Member;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member); //영속상태로 변경
    }
}
