package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    // 하나만 찾는 것
    // unique하고 null인 프라이머리 키로만 가능.
    // 다음 컬럼으로 찾고 싶으면 jpql로 해야 함.
    public Member find(String id) {
       return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }



}
