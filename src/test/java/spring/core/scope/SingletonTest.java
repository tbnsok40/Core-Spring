package spring.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {
    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {
        @PostConstruct
        public void init() { // 여기 init, detroy 메서드 명은 커스텀가능, 중요한건 어노테이션
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}

// prac1
//public class SingletonTest{
//    @Test
//    void singletonBeanFind() {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
//        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
//        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
//        assertThat(singletonBean1).isSameAs(singletonBean2); // 같다.
//        ac.close();
//    }
//    // spring container 는 기본적으로 singleton container 이기에 굳이 어노테이션으로 singleton임을 붙이지 않아도 될
//    static class SingletonBean{
//        @PostConstruct
//        public void init() {
//            System.out.println("Singleton.init");
//        }
//        @PreDestroy
//        public void destory() {
//            System.out.println("Singleton.destory");
//        }
//    }
//}