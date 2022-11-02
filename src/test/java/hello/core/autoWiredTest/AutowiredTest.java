package hello.core.autoWiredTest;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);

    }

    static class TestBean{

        //기본값은 required = true
        //@Autowired(required = false)을 설정 할 경우 의존관계가 없으면 메소드 자체가 실행이 되지 않음.
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        //null이어도 호출은 되나 null로 들어옴.
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        //java8에서 지원. 스프링 빈이 없으면 'Optional.empty'로 표현. 값이 있으면 Optional로 감싸져서 나옴.
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }

        //@Nullable, Optional은 스프링 전반에서 적용됨.
    }
}
