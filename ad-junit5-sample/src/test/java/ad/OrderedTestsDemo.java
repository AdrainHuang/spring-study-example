package ad;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @Author AdrainHuang
 * @Date 2019/9/15 23:24
 * 私有变量之间不共享
 **/
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
public class OrderedTestsDemo {
	
	private int n = 1;
	private static int x = 1;
	@Test
	@Order(1)
	void nullValues() {
		log.info("{}", n++);//1
		log.info("{}", x++);//1
	}
	
	@Test
	@Order(2)
	void emptyValues() {
		log.info("{}", n++);//1
		log.info("{}", x++);//2
	}
	
	@Test
	@Order(3)
	void validValues() {
		log.info("{}", n++);//1
		log.info("{}", x++);//3
	}
}
