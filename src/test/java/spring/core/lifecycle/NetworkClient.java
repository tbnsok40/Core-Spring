package spring.core.lifecycle;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient{
    public String url;
    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        // connect();
        // call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    public void disconnect() {
        System.out.println("close = " + url);
    }
    @PostConstruct
    public void init(){
        // 스프링 의존관계 주입 끝난 후 실행
        System.out.println("NetworkClient.afterPropertiesSet");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy // @PostConstruct & @PreDestroy 이러면 끝
    public void close() {
        System.out.println("NetworkClient.destroy");
        // bean 이 종료될 때 호출
        disconnect();
    }
}
