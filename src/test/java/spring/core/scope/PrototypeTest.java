package spring.core.scope;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {
    @Test
    void prototypeBeanBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        System.out.println("find prototypeBean1");
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("find prototypeBean2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close(); // close가 안됐다 -> 이름 그대로 프로토 타입을 만들고 버렸기 때문에
    }
    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() { // 여기 init, destroy 메서드 명은 커스텀가능, 중요한건 어노테이션
            System.out.println("PrototypeBean.init");
        }
        @PreDestroy // @prototype 은 destroy 되지 않는다. (종료 메서드가 호출되지 않는다)
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}

// prac1
//public class PrototypeTest{
//    @Test
//    void prototypeBeanFind() {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
//        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
//        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
//        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
//        ac.close();
//    }
//    @Scope("prototype") // 이게 없으면 그냥 싱글톤 패턴 -> 같은 객체 생성/**/
//    static class PrototypeBean {
//        @PostConstruct
//        public void init() {
//            System.out.println("PrototypeBean.init");
//        }
//    }
//}