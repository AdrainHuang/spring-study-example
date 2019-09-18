package ad;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @Author AdrainHuang
 * @Date 2019/9/12 01:05
 **/
@DisplayName("A special test case")
public class DisplayNameDemo {
	
	@Test
	@DisplayName("Custom test name containing spaces")
	void testWithDisplayNameContainingSpaces() {
	}
	
	@Test
	@DisplayName("╯°□°）╯")
	void testWithDisplayNameContainingSpecialCharacters() {
	}
	
	@Test
	@DisplayName("😱")
	void testWithDisplayNameContainingEmoji() {
	}
	
}
