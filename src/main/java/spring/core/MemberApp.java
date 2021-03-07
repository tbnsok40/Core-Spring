package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        // Appconfig에 의존성 주입한 경우
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // spring container 사용한 경우
        // ApplicationContext == 스프링 컨테이너
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // appconfig의 bean을 spring container에 등록시켜준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);// appconfig의 bean을 가져온다.

//        MemberService memberService = new MemberServiceImpl(); // appConfig가 생기면서 지운다.
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

// MemoryMemberRepository => MemberRepository : 저장(save()) 및 찾기(findById())
// MemberServiceImpl => MemberService : 회원 생성(join) 및 찾기(findMember())

// 멤버들을 만들고, 주문을 하고, 주문에 할인을 넣기
// 학생을 만들고, 수강신청을 하고, 성적 부여하기