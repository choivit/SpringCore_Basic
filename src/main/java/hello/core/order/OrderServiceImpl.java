package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
// @RequiredArgsConstructor 이 어노테이션은 생성자를 자동으로 만들어줌. 추가를 할때 필드만 추가를 하면 돼서 정말 편함.
public class OrderServiceImpl implements OrderService{

    //생성자 주입 방식을 선택하는 이유 중 가장 큰 이유는 프레임워크에 의존하지 않고 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하기 때문
    // 기본으로 생성자 주입을 사용하고 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 됨. 생성자 주입과 수정자 주입을 동시에 사용 가능.
    //항상 생성자 주입을 선택하고 가끔 옵션이 필요할 경우 수정자 주입을 선택하는 게 좋음. 필드 주입은 가급적 사용하지 않는 것이 좋음.
//    ====================================================================================
    /*생성자 주입*/
    //    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //변경 전
//    private DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private DiscountPolicy discountPolicy = new FixDiscountPolicy();

    //인터페이스에만 의존하도록 변경 후(DIP를 지켰다.)
    private final DiscountPolicy discountPolicy;

    //생성자
//    @Autowired //생성자가 하나일때 생략가능
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
//
    @Autowired //생성자가 하나일때 생략가능
    // @Autowired의 특이점은 빈이 여러개일 경우 파라미터나 필드의 변수명을 찾아서 매칭해줌.
    // 1. 타입 매칭
    // 2. 타입 매칭의 결과가 2개 이상일 때 필드 명으로 빈 이름 매칭
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy rateDiscountPolicy) {
    // 스프링은 자동보다는 수동이 우선순위기 때문에 자동인 @Primary 보다는 섬세하게 수동으로 지정을 해준 @Qualifier가 우선순위다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

//    ====================================================================================
    /*수정자 주입*/
//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//
//    //수정자
//    @Autowired(required = false)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        System.out.println("setDiscountPolicy = " + discountPolicy);
//        this.discountPolicy = discountPolicy;
//    }
//
//    //수정자
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        System.out.println("setMemberRepository = " + memberRepository);
//        this.memberRepository = memberRepository;
//    }

//    ====================================================================================
    /*자바빈 프로퍼티*/

//    ====================================================================================
    /*필드 주입*/
    //외부에서 확인이 불가능해서 좋은 방법이 아님.

//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    //    ====================================================================================
    /*일반 메서드 주입*/

//    private MemberRepository memberRepository;
//    private DiscountPolicy discountPolicy;
//    @Autowired
//    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    ====================================================================================

//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice , discountPrice);
    }

    //테스트용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
