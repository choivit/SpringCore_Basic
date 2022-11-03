package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
// @Qualifier는 이름을 바꿔주는 것이 아니고 옵션을 하나 더 추가해주는 것.
// 강사님 경험상 @Qualifier는 @Qualifier를 찾는 용도로만 사용하는 것이 명확하고 좋음.
// @Qualifier 정리
// 1. @Qualifier끼리 매칭
// 2. 빈 이름 매칭
// 3. NoSuchBeanDefinitionException 예외 발생
@MainDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy{

    private int dicountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * dicountPercent / 100;
        } else {
            return 0;
        }
    }
}
