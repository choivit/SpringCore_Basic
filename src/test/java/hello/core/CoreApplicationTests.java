package hello.core;

import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {

	//테스트 코드는 나만 테스트하는데 쓰면 돼서 쓰는 경우도 종종!
	@Autowired
	OrderService orderService;

	@Test
	void contextLoads() {
	}

}
