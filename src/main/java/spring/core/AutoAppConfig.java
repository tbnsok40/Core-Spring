package spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        // basePackages 를 지정하지 않으면, @ComponentScan이 붙은 설정 정보클래스의 패키지의 시작위치가 된다(spring.core) --> 관례상 가장 루트 디렉터리 패키지에 AutoAppConfig 생성
        //basePackages = "spring.core.member", // 이 위치에서부터 시작 => member 패키지만 컴포넌트의 대상이 된다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)// 제외할 컴포넌트 : AppConfig.class <= 수동으로 등록해놓은 것이기에, 제외하지 않으면 자동 등록과 충돌날 수 밖에 없다
public class AutoAppConfig {
}

// 기존 AppConfig 와 달리 @Bean 으로 등록할 클래스가 하나도없다.
// 보통 실무에선 제외하지 않지만, 여긴 기본예제니까.

// @ComponentScan이 선언되면, @Component라는 클래스를 모두 뒤진다 => 스프링 빈으로 등록한다.
// 스프링 빈의 기본 이름은 클래스명으로 사용하되 맨 앞글자만 소문자를 사용한다.
// 이름을 직접 부여하고 싶으면 @Component("memberService3") 이런식으로 커스텀 가능 : 왠만하면 디폴트를 쓰거라

// 생성자에 @Autowired 를 지정하면, 스프링 컨테이너가 자동으로 해당 스프링 빈을 찾아서 주입 + 생성자에 파라미터가 많아도 자동으로 주입

// @애노테이션은 상속,연계 기능이 전혀 없다. 애노테이션이 다른 애노테이션을 인식할 수 있는 것은 스프링이 지원하는 기능이기 때문
// @애노테이션은 메타정보
// @service는 특별한 처리를 하지 않는다. 개발자들이 비즈니스로직이 여기있음을 인지할 수 있을 정도.