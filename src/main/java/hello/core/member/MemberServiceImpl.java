package hello.core.member;

public class MemberServiceImpl implements MemberService{
    // 의존성에 대한 고민은 외부에 맡기고 실행에만 집중

    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //생성자를 통해서 뭐가 들어갈지 결정을 해주는 것.
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
}
