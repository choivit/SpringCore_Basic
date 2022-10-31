package hello.core.singleton;

public class SingletonService {

    // 이렇게 적으면 클래스 레벨에서 하나만 존재하게 됨.
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
