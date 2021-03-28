package spring.core.discount;

import org.springframework.stereotype.Component;
import spring.core.member.Grade;
import spring.core.member.Member;

@Component
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}

// 이름만 다르고 완전히 똑같은 타입의 스프링 빈이 2개 있을 때 해결이 안된다.
// RateDiscountPolicy에도 @Component가 있기 때문에 동일한 빈에 두번의 컴포넌트 스캔이 들어감.
