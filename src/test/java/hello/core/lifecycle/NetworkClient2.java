package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
// javax는 스프링이 아닌 자바에서 공식적으로 지원하는 것.

public class NetworkClient2 {

    private String url;

    public NetworkClient2(){
        System.out.println("생성자 호출, url = " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public void call(String message){
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("close: " + url);
    }

    // 1, 2, 3번째 방법 중에 가장 추천되는 방법임.

    @PostConstruct
    //의존 관계 주입이 끝나면 세팅을 해주겠다는 것.
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    //이 빈이 종료될때 호츨이 됨.
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }

    //가장 사용성이 좋으나 코드를 고칠 수 없는 외부 라이브러리를 초기화, 종료해야 하면 @Bean의 'initMethod', 'destroyMethod'를 사용하자.
}
