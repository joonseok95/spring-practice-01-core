package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o() {
        //given
        Member memberVIP = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(memberVIP, 9500);

        //then
        Assertions.assertThat(discount).isEqualTo(950);

    }

    @Test
    @DisplayName("BASIC이면 할인 x")
    void basic_o() {
        //given
        Member memberBasic = new Member(1L, "memberBasic", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(memberBasic, 9500);

        //then
        Assertions.assertThat(discount).isEqualTo(0);
    }
}