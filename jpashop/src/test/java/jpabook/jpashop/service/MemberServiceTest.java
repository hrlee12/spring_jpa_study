package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(SpringRunner.class)
// SpringBoot기능을 사용하면서 Test  ex)@Autowired
// 스프링 컨테이너 안에서 테스트 돌림.
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    // 1) insert 쿼리문 확인하는 방법 : db에도 반영
    // test에서는 기본으로 rollback
    // 조회는 트랜잭션을 커밋하지 않아도 sql문이 실행되지만
    // insert등 db를 변경하는 내용은 트랜잭션을 커밋할 때 플러쉬 되면서 실행됨.
    // 그런데 @Transactionl의 기본 설정(test에서)은 Rollback임. 즉, 커밋이 안 됨.
    // 그래서 임의로 @Rollback(false)설정을 해주어야 됨.
//    @Rollback(false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("lee");

        //when
        Long saveId = memberService.join(member);

        //then
        // 2)insert쿼리문 확인하고 싶을 때 방법
        // 쿼리문은 날리지만 rollback을 해버림.
        em.flush();
        // 같은 트랜잭션 안에서 영속성 컨텍스트는 프라이머리 키가 같으면 하나의 엔티티로 관리가 됨.
        // 매개변수가 두개가 같으면 true
        assertEquals(member, memberRepository.findOne(saveId));

    }

    // try~catch를 대체해서
    // @Test(expected = 예외이름.class)로 대체 가능
    // 예외를 만난 순간 return
    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2);
//        try {
//            memberService.join(member2); // 예외가 발생해야 한다!!
//        } catch(IllegalStateException e) {
//            return;
//        }

        //then
        // 실패해야 하는 코드인데 성공했을 때 쓰는 녀석
        fail("예외가 발생해야 한다.");
    }
}