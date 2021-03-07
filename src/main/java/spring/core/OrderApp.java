package spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.member.Grade;
import spring.core.member.Member;
import spring.core.member.MemberService;
import spring.core.member.MemberServiceImpl;
import spring.core.order.Order;
import spring.core.order.OrderService;
import spring.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        OrderService orderService = appConfig.orderService();
//        MemberService memberService = appConfig.memberService();
// spring으로 전환


        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // appconfig의 bean을 spring container에 등록시켜준다.
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

//        OrderService orderService = new OrderServiceImpl();
//        MemberService memberService = new MemberServiceImpl();

        Long memberId = 1L;
        Member member = new Member("memberA", memberId, Grade.VIP);
        memberService.join(member); // psvm 밖에선 에러나네..?
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println(order);

    }
}
