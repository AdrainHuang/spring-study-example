package ad;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

/**
 * @Author AdrainHuang
 * @Date 2019/9/15 23:56
 * 只为存在一个实例变量，所以 n的值会从1-3
 * @see OrderedTestsDemo
 **/
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class SingleInstanceDemo {
	
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
		log.info("{}", n++);//2
		log.info("{}", x++);//2
	}
	
	@Test
	@Order(3)
	void validValues() {
		log.info("{}", n++);//3
		log.info("{}", x++);//3
	}
}
