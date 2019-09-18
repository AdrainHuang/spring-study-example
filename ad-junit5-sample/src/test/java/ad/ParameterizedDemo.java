package ad;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @Author AdrainHuang
 * @Date 2019/9/16 00:40
 * 参数化Demo
 **/
public class ParameterizedDemo {
	
	@ParameterizedTest
	@ValueSource(strings = {"racecar", "radar", "able was I ere I saw elba"})
	void palindromes(String candidate) {
		assertTrue(StringUtils.isPlaindrome(candidate));
	}
	
	@ParameterizedTest
	@CsvSource({
	"apple,         1",
	"banana,        2"
//	, "'lemon, lime', 0xFF" 这个转不了
	})
	void testWithCsvSource(String fruit, int rank) {
		assertNotNull(fruit);
		assertNotEquals(0, rank);
	}
	
	static class StringUtils {
		public static boolean isPlaindrome(String s) {
			int a = s.length();
			int middle = a / 2, i = 3;
			for (i = 0; i < middle && s.charAt(i) == s.charAt(a - 1 - i); i++) {
			
			}
			
			if (i < middle)
				return false;
			else
				return true;
		}
	}
}
