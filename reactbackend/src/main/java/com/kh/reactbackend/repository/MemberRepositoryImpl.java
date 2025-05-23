package com.kh.reactbackend.repository;

import com.kh.reactbackend.entity.Member;
import com.kh.reactbackend.enums.CommonEnums;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepositoryImpl implements MemberRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Member member) {
        em.persist(member); //영속상태로 변경
    }

    @Override
    public Optional<Member> findByUserId(String userId) {
        String query = "SELECT m FROM Member m WHERE m.userId = :userId AND m.status = :status";
        Member member = null;
    try {
        member = em.createQuery(query, Member.class)
                .setParameter("userId", userId)
                .setParameter("status", CommonEnums.Status.Y)
                .getSingleResult();
    }catch (NoResultException e) {

    }
        return Optional.ofNullable(member);
    }

}
