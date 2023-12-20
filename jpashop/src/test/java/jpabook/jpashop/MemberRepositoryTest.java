package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

// @RunWith: junit으로 어떤 녀석을 테스트? String 관련된 걸로 테스트
@RunWith(SpringRunner.class)
// 테스트임을 나타냄.
@SpringBootTest
public class MemberRepositoryTest {
    @Autowired
    MemberRepository memberRepository;

    @Test
    // 엔티티 매니저는 트랜잭션 안에서만 생성됨.
    // 그래서 트랜잭션 생성
    // 트랜잭션 끝난 후 롤백해버림.
    @Transactional
    @Rollback(false)
    public void testMember() throws Exception {
        //given
        //변수 준비
        Member member = new Member();
        member.setUsername("memberC");

        //when
        //메서드 실행
        long saveId = memberRepository.save(member);
        Member findMember = memberRepository.find(saveId);

        //then
        //검증

        // Assertions.assertThat(객체).isEqualsTo(객체)
        // Test하는 코드
        // 참이면 테스트 통과/오류면 테스트 오류
        // 객체의 내용 자체를 비교함.

        // 이미 영속성 컨텍스트에 memberA 엔티티가 저장되어 있으므로 1차 캐시에서 확인하고 가져옴
        // DB에 다시 접근하지 않음.
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        // 엔티티는 프라이머리 키를 기준으로 같은 엔티티인지 구분
        Assertions.assertThat(findMember).isEqualTo(member);
    }

}