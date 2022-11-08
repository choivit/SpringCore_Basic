package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest(){
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
//        NetworkClient client = ac.getBean(NetworkClient.class);
//        NetworkClient1 client = ac.getBean(NetworkClient1.class);
        NetworkClient2 client = ac.getBean(NetworkClient2.class);
        ac.close();
        //이전과는 다르게 close를 적용해볼건데
        // 상단에 AnnotationConfigApplicationContext의 정의방법으로 ApplicationContext는 close를 사용할 수 없음.
        // 따라서 AnnotationConfigApplicationContext 혹은 ConfigurableApplicationContext로 적용을 해줘야 사용 가능함.
    }

    @Configuration
    static class LifeCycleConfig{
        //@Bean으로 등록을 할때 close에는 특별한 기능이 있다.
        //inferred라는 기능인데 이름 그대로 'close', 'shutdown'이라는 이름을 자동으로 호출해준다.
        //종료를 하고싶지 않다면 'destroyMethod = ""' 처럼 빈 공백으로 등록을 해주면 된다.
        @Bean(initMethod = "init", destroyMethod = "close")
//        public NetworkClient networkClient(){
        public NetworkClient2 networkClient(){
//            NetworkClient networkClient = new NetworkClient();
//            NetworkClient1 networkClient = new NetworkClient1();
            NetworkClient2 networkClient = new NetworkClient2();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}