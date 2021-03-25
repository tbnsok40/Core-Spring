package spring.core.order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring.core.discount.DiscountPolicy;
import spring.core.discount.FixDiscountPolicy;
import spring.core.discount.RateDiscountPolicy;
import spring.core.member.Member;
import spring.core.member.MemberRepository;
import spring.core.member.MemoryMemberRepository;


// 정리: 최근에는 생성자를 딱 1개를 두고, lombok 라이브러리를 사용하여 코드를 깔끔하게 만드는게 트랜드
@Component
@RequiredArgsConstructor // 생성자를 지워벌인다..!!! 개신기하네 --> requiredArgs: final keyword가 붙은 필드, Constructor: 생성자 / 임의의 필드(final)가 추가되는 상황에서 매우 편리하다(자동주입)
public class OrderServiceImpl implements OrderService {

//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //고정 할인이 아닌 rateDiscount로 바꾸고 싶으면, new 이하 부분만 갈아끼우면 된다.
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 요렇게
    // 할인정책을 변경하려면 클라인 OrderServiceImpl코드를 고쳐야 한다.
    // 추상 (인터페이스) 뿐만 아니라 구체(구현) 클래스에도 의존하고 있다. (fixdiscountpolicy, ratediscountpolicy) --> DIP, OCP 위반
    // 클라이언트 코드에 손대서 문제인 것..?

    // final keyword가 붙으면 필수값이 된다.
    // field -> 수정하려면 final 빼고 setter 사용하여 수정한다 => @Autowired 붙여줘야함
    private final DiscountPolicy discountPolicy; // 이렇게 바꾼다 (DIP는지킨 상태) => 이 상태면 nullpointerException
//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 마찬가지로, 저장소(db)를 바꾸고 싶으면, new 이하만 바꾸면 된다. //AppConfig에 등록했으니 지워준다
    private final MemberRepository memberRepository;

    // 생성자엔 null 할당 X (관례상)
//    @Autowired // 생성자에 @autowired 어노테이션 붙인다. -> 생성자 하나일 때는 autowired 생략 가능
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) { // 철저히 인터페이스에만 의존하고 있다.
//        this.discountPolicy = discountPolicy;
//        this.memberRepository = memberRepository;
//    }
    // 생성자가 한개만 있을 땐, @Autowired를 생략해도 자동주입된다.

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // orderService는 discountPolicy의 내용을 전혀 몰라도 되며, discount interface에 전적으로 역할을 다 넘겼다.
        // 역할과 구현이 완벽히 잘 분리되어있다. (분리만 잘 돼 있다)
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
// 필드 주입 -> 안티 패턴: 쓰지 않는다. DI 프레임워크가 없으면 아무것도 할 수 없다.