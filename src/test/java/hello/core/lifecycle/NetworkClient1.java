package hello.core.lifecycle;

public class NetworkClient1 {

    private String url;

    public NetworkClient1(){
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

    //의존 관계 주입이 끝나면 세팅을 해주겠다는 것.
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    //이 빈이 종료될때 호츨이 됨.
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();

    }
}
