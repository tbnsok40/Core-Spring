package spring.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService(); // ㅈㅏ기자신을 객체로 만들어 instance에 참조시킨다.
    // 이 객체 인스턴스가 필요한면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
    public static SingletonService getInstance() {
        return instance;
    }

    // 이게 private으로 돼있다는 것은, singleton을 지키기 위함을 인지할 수 있어야한다.
    private SingletonService() {
    }


    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
