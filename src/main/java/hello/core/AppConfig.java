package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

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

    //memberService 역할
    public MemberService memberService(){
        return new MemberServiceImpl(memberRepository());
    }

    //memberRepository 역할
    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //memberRepository 역할
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy(){
        return new FixDiscountPolicy();
    }

}
