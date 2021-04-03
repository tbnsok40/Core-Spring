package spring.core.web;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.core.common.MyLogger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // 얘는 웹 스코프(@Scope = "request") 이므로 request 가 없으면 생성주기를 시작도 못해, 그래서 지금 run 하면 constructor 에 주입조차 안된 것.

    private final ObjectProvider<MyLogger> myLoggerProvider; //provider 로 선언


   // 즉 스프링컨테이너에게 myLogger 빈을 요청하는 시점이, 의존관계 주입단계가 아닌 실제 고객에게 요청이 올 때로 지연시켜야한다 => Provider 사용
    // (myLogger 요청 시점은 생성자에 주입될 때가 아닌, request 요청이 올 때로 늦춰한다 => Provider 어노테이션 사용)
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        Thread.sleep(1000); // 1초 후에 아래 코드가 실행되도 같은 uuid를 출력함을 확인 할 수 있다.
        logDemoService.logic("testId");
        return "OK";

        // 'OK' 뜬 브라우저에서 새로고침 하면 HTTP 요청마다 4개의 UUID가 같은 메시지가 뜨는 것을 알 수 있다
    }
}
