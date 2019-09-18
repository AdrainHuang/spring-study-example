package ad;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.api.condition.EnabledIf;

/**
 * @Author AdrainHuang
 * @Date 2019/9/15 23:14
 **/
@Slf4j
public class ScriptConditinDemo {

	@Test
	@EnabledIf("2 * 3  == 6")
	void willExecuted(){
		log.info("666");
	}
	
	@RepeatedTest(10) // Dynamic JavaScript expression.
	@DisabledIf("Math.random() < 0.314159")
	void mightNotBeExecuted() {
		log.info("run");
	}
}
