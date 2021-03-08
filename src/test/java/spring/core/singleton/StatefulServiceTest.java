package spring.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefulServiceSingleton() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //ThreadA : a사용자가 10000 주문
//        statefulService1.order("userA", 10000);
//        //ThreadA : b사용자가 20000 주문
//        statefulService2.order("userB", 20000);


        int Auser = statefulService1.order("userA", 10000);
        int Buser = statefulService2.order("userB", 20000);


        // singleton 방식으로 인하여, stateful1, 2가 같은 객체가 돼버린다. A가 주문한 10000이 --> 20000으로 바뀌어 버린다.

//        int price = statefulService1.getPrice();
//        System.out.println(price); // 10000이 나올것 같지만 20000이 나온다
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);

        // 같은 객체를 호출했지만, 서로 다른 결과를 리턴한다.stateless상태
        Assertions.assertThat(Auser).isNotSameAs(Buser);

    }

    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }



}
