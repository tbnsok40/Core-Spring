package spring.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.core.AutoAppConfig;
import spring.core.discount.DiscountPolicy;
import spring.core.member.Grade;
import spring.core.member.Member;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


// 배운점: test 실행했는데 에러가 터졌고, NoUniqueBeanDefinitionException 이란 exception이 터졌다.
// 이유인 즉슨, 스프링 빈은 타입으로 조회하는데, fixDiscountPolicy와 rateDiscountPolicy 모두에 @Component 어노테이션이 붙어있었기 때문이다(서로 다른 빈이지만 같은 타입의 빈)
// 그렇기 때문에 AutoComponentScan이 돌면서 2개의 동일 타입 빈을 모두 조회해버렸고, 에러가 터진 것이다. => 한쪽의 @Component 를 주석 처리하면서 해결
public class AllBeanTest {

    @Test
    void findAllBean() {

        // 이건 아래의 DiscountService만 땡겨온것이기에 출력하면 빈칸 밖에 없다. 그러므로 AutoAppConfig도 주입해줘야한다.
//        ApplicationContext ac = new AnnotationConfigApplicationContext(DiscountService.class); //spring bean 등록

        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member("userA", 1L, Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }


    // 새로운 서비스를 만든다 (기존 코드 건드리지 않도록)
    static class DiscountService {
        private final Map<String, DiscountPolicy> policyMap; // map으로 모든 discountpolicy를 주입받는다.
        private final List<DiscountPolicy> policies;

        // 생성자로 필드 주입받는다.

        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) { // 할인 코드를 빈 이름과 매칭
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);
            return discountPolicy.discount(member, price);
        }
    }


}
