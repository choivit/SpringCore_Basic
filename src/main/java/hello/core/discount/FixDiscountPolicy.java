package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
// @Primary 여러개의 선택지가 있을때 우선순위로 지정을 해줌.
public class FixDiscountPolicy implements DiscountPolicy{

    private int dicountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return dicountFixAmount;
        }else {
            return 0;
        }
    }
}
