package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정정보가 있는 곳에 Configuration을 넣으면 싱글톤이 보장된다.
@Configuration
public class AppConfig {

    //AppConfig는 공연기획자와 같은 역할. 구체적인 건 여기서 선택을 해주기. 예)배우캐스팅
    //너무 많은 책임을 지고 있었던 Impl클래스의 역할과 책임을 적절하게 나누어 단일 책임 원칙을 지킬 수 있음.
//    public MemberService memberService(){
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService(){
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }

//  ========================================================================================================

    //하지만 역할과 구현은 또 정확하게 나뉘어야 하기 때문에
    //MemoryMemberRepository()라는 보이지 않기 때문에

    //@Bean(name = "직접설정한 이름")으로 옵션 지정 가능.

    // 예상 실행 시나리오
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository

    // 실제 실행 시나리오
    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    //이런 현상은 위에 @Configuration 때문임.

    @Bean
    //memberService 역할
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    //memberRepository 역할
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    //memberRepository 역할
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
