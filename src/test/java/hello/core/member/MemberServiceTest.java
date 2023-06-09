package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static hello.core.member.Grade.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void beforeEach() {
        AppConfig appConfig = new AppConfig();
        this.memberService = appConfig.memberService();
    }

    @Test
    void join() {
        //given
        Member memberA = new Member(1L, "memberA", VIP);

        //when
        memberService.join(memberA);

        //then
        Member findMember = memberService.findMember(memberA.getId());
        Assertions.assertThat(findMember).isEqualTo(memberA);
    }

}