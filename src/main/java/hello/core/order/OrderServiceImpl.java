package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

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
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
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
