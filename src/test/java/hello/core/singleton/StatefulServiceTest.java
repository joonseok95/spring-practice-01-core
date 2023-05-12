package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;


class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //A사용자가 주문하고 주문금액 조회하던중에 B사용자가 주문을 한 상황.

        //ThreadA : A사용자 1000원 주문
        statefulService1.order("userA", 1000);
        //ThreadA : B사용자 1500원 주문
        statefulService2.order("userB", 1500);

        //ThreadA : 사용자A 주문 금액 조회
        int price = statefulService1.getPrice();
        Assertions.assertThat(price).isEqualTo(1500);

        //1000원이 나왔으면 좋겠지만 싱글톤이기때문에 1500이 나온다

    }

    static class TestConfig {


        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}