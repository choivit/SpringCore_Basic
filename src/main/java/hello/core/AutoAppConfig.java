package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
//@ComponentScan가 있으면 자동으로 @Component가 붙은 클래스를 스프링빈 등록해줌.
//그런데 자동으로 등록이 되면 의존관계를 지정할 수 없음. 그래서 @Autowired로 의존관계 자동주입으로 주입을 해줌.
@ComponentScan(
//        basePackages = "hello.core.member",
        //basePackages 이걸 지정을 해주면 탐색할 패키지를 지정가능하며 해당 패키지 안에서만 실행이 됨.
        //여러개도 지정 가능.
//        basePackageClasses = AutoAppConfig.class,
        //basePackageClasses는 지정한 클래스의 패키지를 탐색 시작 위치로 지정.
        //지정을 하지 않으면 default는 @ComponentScan이 있는 클래스의 패키지가 시작 위치가 되어 하위 패키지까지 모두 탐색.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        //제외할 대상 선정
        //여기서 제외를 한 이유는 기존의 코드를 보존하기 위해 부득이하게 어노테이션은 제외했으나 보통은 제외하진 않음.
)

public class AutoAppConfig {
}
