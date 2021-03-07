package spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.member.MemberRepository;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.member.MemoryMemberRepository;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;


// 애플리케이션 실제 동작에 필요한 구현 객체를 직접 생성 (이전에는 각  impl코드에서 구현 객체 생성(할)

@Configuration
public class AppConfig {

    // memberService, OrderService는 인터페이스
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//        // 생성자 MemberServiceImpl에 구현객체(MemoryMemberRepository를 주입) in AppConfig
//    }
//
//    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }
//

    // Refactoring => 역할과 구현 클래스가 한눈에 들어온다.
    // Bean으로 등록된 객체는 모두 인터페이스(역할) => 구현체는 인터페이스 메서드의 리턴체
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    // 할인 정책 바꿀 때 아래 코드만 수정하면 된다.
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
//    @Bean을 붙임으로 스프링 컨테이너에 등록하게 된다.

