package jpabook.jpashop.service;



import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// @Transactional : 트랜잭션 생성
// 이 어노테이션을 안 붙여주면 기본적으로 트랜잭션이 생성되지 않음.
// 클래스 단에 추가하면 각 public 메소드에 적용됨.
// 트랜잭션이 있어야 dirtyCheck도 해서 알아서 DB에 저장도 해줌.
@Transactional
// final이 붙은 멤버 변수만 생성자에서 초기화
// 직접 생성자를 작성하지 않아도 어노테이션만으로도 의존성 주입이 된다.
@RequiredArgsConstructor
public class MemberService {

    // final을 붙임으로써 생성자에서 값 할당되는 것을 컴파일러에서 체크
    private final MemberRepository memberRepository;



    // 회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 중복 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());

        if (!findMembers.isEmpty())
            // IllegalStateException : 부적절한 매개변수를 건내줬을 때 발생.
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    // 회원 전체 조회
    // 조회만 한는 경우 readOnly = true 옵션 사용하기
    // 성능 최적화
    // 플러쉬(영속성 컨텍스의 내용을 DB에 저장) 안함. 더티 체크 안함.
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    @Transactional(readOnly = true)
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

}
