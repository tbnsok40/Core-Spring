package spring.core.discount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import spring.core.member.Grade;
import spring.core.member.Member;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용")
    void vip_o(){
        //given
        Member member = new Member("memberVIP", 1L, Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);
        // Assertions 에서 opt+enter눌러 static으로 상위로 뺀다.

    }
    // 실패테스트도 꼭 만들어야한다.

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않는다.")
    void vip_x(){
        //given
        Member member = new Member("memberVIP", 1L, Grade.VIP);

        //when
        int discount = discountPolicy.discount(member, 10000);

        //then
        assertThat(discount).isEqualTo(1000);

    }
}

// 할인과 관련된 기능을 잘 빼놨기에(구조상) 쉽게 할인 정책을 변경할 수 있다.