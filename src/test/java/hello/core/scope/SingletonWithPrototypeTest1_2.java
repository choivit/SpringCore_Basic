package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1_2 {

    //스프링 컨테이너에 프로토타입 빈 직접 요청
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    //default가 싱글톤이라 안해줘도 되지만
    @Scope("singleton")
    static class ClientBean{

        @Autowired
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider;
//        private ObjectFactory<PrototypeBean> prototypeBeanProvider;
        private Provider<PrototypeBean> prototypeBeanProvider;
        //ObjectFactory가 과거에 있고 편의기능을 추가하여 ObjectProvider가 만들어짐.
        // prototypeBeanProvider.getObject를 호출하면 그때 스프링 컨테이너에서 프로토타입 빈을 찾아서 호출해줌.

        public int logic(){
//            PrototypeBean prototypeBean = prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("singleton")
    static class ClientBean2{
        private  final PrototypeBean prototypeBean; //생성시점에 주입 x@2


        //생성자가 하나라 굳이 @Autowired 필요없음.
        @Autowired
        ClientBean2(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic(){
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    // 위와 같은 경우에는 주입이 될때마다 새로 생성됨. 그러나 사용될 때마다 새로 생성되는 것은 아님.



    @Scope("prototype")
    static class PrototypeBean{
        private int count = 0;
        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }

    //
}
