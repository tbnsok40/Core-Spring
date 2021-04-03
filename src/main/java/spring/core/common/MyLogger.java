package spring.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // 원래 value =  생략할 수 있지만, 2개 이상의 인자가 들어오면 명시해줘야 한다 (생략 불가)
public class MyLogger { // log를 출력하기 위한 mylog 클래
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + "[" + message + "]");
    }
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString(); // 이 빈은 HTTP 요청당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }
    @PreDestroy
    public void close() { // http 요청이 끝나는 시점에 소멸된다
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
