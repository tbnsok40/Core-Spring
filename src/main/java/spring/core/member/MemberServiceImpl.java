package spring.core.member;

// impl: 구현체

public class MemberServiceImpl implements MemberService {
    // MemberServiceImpl 자체도 구현체인데, 아래에서 MemberMemberRepository 라는 또 다른 구현체를 할당 받고 있다. (의존 문제)
    private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
