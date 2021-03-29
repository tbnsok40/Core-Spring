package spring.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {
    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        // ConfigurableApplicationContext 은 AnnotationConfigApplicationContext의 부모 관계이기 때문에 위와 같이 지정 가능
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // configurableApplicationContext 를 해주어야 close 를 제공받을 수 있다.
    }

    @Configuration
    static class LifeCycleConfig {
//        @Bean(initMethod =  "init", destroyMethod = "close") // Implements 대신 간편한 방
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // 1. 스프링 빈 등록할 때 생성자 호출
            networkClient.setUrl("http://hello-spring.dev"); // 2. 객체를 모두 생성한 후에 url을 넣어줬다. (1에서 url 은 null 상태)
            // setter 를 수정자 라고 한다.
            return networkClient;
        }
    }
}
