package spring.core.order;

import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정 할인이 아닌 rateDiscount로 바꾸고 싶으면, new 이하 부분만 갈아끼우면 된다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 요렇게
    // 할인정책을 변경하려면 클라인 OrderServiceImpl코드를 고쳐야 한다.
    // 추상 (인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다. (fixdiscountpolicy, ratediscountpolicy) --> DIP, OCP 위반
    // 클라이언트 코드에 손대서 문제인 것..?

    private final DiscountPolicy discountPolicy; // 이렇게 바꾼다 (DIP는지킨 상태) => 이 상태면 nullpointerException


//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 마찬가지로, 저장소(db)를 바꾸고 싶으면, new 이하만 바꾸면 된다. //AppConfig에 등록했으니 지워준다

    private final MemberRepository memberRepository;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 철저히 인터페이스에만 의존하고 있다.
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // orderService는 discountPolicy의 내용을 전혀 몰라도 되며, discount interface에 전적으로 역할을 다 넘겼다.
        // 역할과 구현이 완벽히 잘 분리되어있다. (분리만 잘 돼 있다)

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
