package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static hello.core.member.Grade.*;

public class OrderApp {

    public static void main(String[] args) {

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", VIP);
        memberService.join(memberA);
        Member findMember = memberService.findMember(memberA.getId());
        Order order = orderService.createOrder(findMember.getId(), "asd", 50000);
        int resultPrice = order.calculatePrice();
        System.out.println(order);
        System.out.println();
        System.out.println();
        System.out.println(resultPrice);

    }
}
