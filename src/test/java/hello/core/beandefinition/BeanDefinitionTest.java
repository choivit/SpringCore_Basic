package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext ac =new AnnotationConfigApplicationContext(AppConfig .class);

    // ApplicationContext는 getBeanDefinition 이게 되지 않아서 다른 걸 더 추가해줘야 해서 안됨.
//    ApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            //scope - 할당이 되어있지 않을때는 싱글톤
            // lazyInit - 보통 스프링빈들은 컨테이너가 뜰때 불러와지지만 이건 사용할때 불러온다는 말
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName
                + " beanDefinition = " + beanDefinition);
            }
        }
    }
}
