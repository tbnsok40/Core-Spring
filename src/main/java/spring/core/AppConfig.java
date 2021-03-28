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


// 애플리케이션 실제 동작에 필요한 구현 객체를 직접 생성 (이전에는 각 impl코드에서 구현 객체 생성(할)

@Configuration //여기가 스프링 컨테이너 자
public class AppConfig {

    // memberService, OrderService는 인터페이스
//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//        // 생성자 MemberServiceImpl에 구현객체(MemoryMemberRepository를 주입) in AppConfig
//    }
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
        return new OrderServiceImpl(discountPolicy(), memberRepository());
    }

    // 할인 정책 바꿀 때 아래 코드만 수정하면 된다.
    @Bean
    public DiscountPolicy discountPolicy() {
        return new FixDiscountPolicy();
    }
}
//    @Bean을 붙임으로 스프링 컨테이너에 등록하게 된다.

// @Bean memberService --> new MemoryMemberRepository();
// @Bean orderService --> new MemoryMemberRepository(); 싱글톤이 깨지는것 아닐까? 각각 다른 2개의 MemoryMemberRepository를 생성하기에 싱글톤을 깨는것 처럼 보인다.
// CGLIB에 의해 한번만 호출된다. 한번 스프링 빈에 등록되면 재호출하지 않는다 => 반대로 아직 등록되지 않은 상태라면 스프링빈에 등록시킨다.

// AppConfig: 빈 등록, @configuration 어노테이션으로 인해 스프링 설정파일로 인식된다. 이게 없으면 싱글톤컨테이너 동작되지 않기에, 싱글톤 패턴 적용되지 않는다.

