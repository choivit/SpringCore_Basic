package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//기본
public class MemberServiceImpl implements MemberService{
    // 의존성에 대한 고민은 외부에 맡기고 실행에만 집중

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //생성자를 통해서 뭐가 들어갈지 결정을 해주는 것.
    @Autowired
    //like. ac.getBean(MemberRepository.class)와 같으나 @Autowired가 기능이 더 많음.
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
