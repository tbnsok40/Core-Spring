package spring.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;
import spring.core.member.Member;

import java.util.Optional;

public class AutoWiredTest {
    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
//        ac.getBean();

    }

    static class TestBean {

        // 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨 -> 그래서 출력되는 것 없다
        @Autowired(required = false)
        public void setNoBean1(Member noBean1) {
            System.out.println("noBean1 = " + noBean1);
        }

        // 자동 주입할 대상이 없으면 Null 이 입력된다. 호출은 되지만 null 이 입력되는 것
        @Autowired
        public void setNoBean2(@Nullable Member noBean2) {
            System.out.println("noBean2 = " + noBean2);
        }

        // Optional<>: 자동 주입 대상이 없으면 Optional.empty 가 입력된다.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3) {
            System.out.println("noBean3 = " + noBean3);
        }

    }
}
