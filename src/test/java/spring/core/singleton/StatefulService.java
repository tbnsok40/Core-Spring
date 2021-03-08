package spring.core.singleton;

public class StatefulService {
    private int price;

//    public void order(String name, int price) {
//        System.out.println(name + " --- " + price); // 10000 --> 20000
//        this.price = price;
//    }// 실무에서 큰일 날 에러

    // 그렇기 때문에 State는 무상태로 설계해야한다. void를 int로 바꾸어 바로 리턴 -> 객체가 값을 저장하고 있지 않기 때문에 10000 이 20000으로 바뀌지 않는다. 즉 무상태(stateless)
    public int order(String name, int price) {
        System.out.println(name + " --- " + price); // 10000 --> 20000
        return price;
    }

    public int getPrice() {
        return price;
    }
}
