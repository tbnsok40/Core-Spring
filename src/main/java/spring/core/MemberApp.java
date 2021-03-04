package spring.core;

import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member("memberA", 1L, Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member);
        System.out.println("find Member = " + findMember.getName());
        // 이렇게 main Method로 테스트하는건 좋은 방법이 아니다.
    }
}
// 현재 코드의 저장소를 다른 것으로 바꿀 때, OCP를 잘 지키고 있는 것인지.
// DIP를 잘 지키고 있는지.