package ad;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author AdrainHuang
 * @Date 2019/9/16 00:02
 * 嵌套测试 @Nested 只有非静态嵌套类（即内部类）才能用作@Nested测试类。嵌套可以任意深
 **/

@DisplayName("A stack")
public class TestingAStackDemo {
	Stack<Object> stack;
	
	List<String> list;
	
	@Test
	@DisplayName("is instantiated with new Stack()")
	void isInstantiatedWithNew() {
		new Stack<>();
	}
	
	@Nested
	@DisplayName("when new")
	class WhenNew {
		
		@BeforeEach
		void createNewStack() {
			stack = new Stack<>();
			list = new ArrayList<>();
			list.add("a");
		}
		
		@Test
		@DisplayName("is empty")
		void isEmpty() {
			assertTrue(stack.isEmpty());
		}
		
		@Test
		@DisplayName("throws EmptyStackException when popped")
		void throwsExceptionWhenPopped() {
			assertThrows(EmptyStackException.class, stack::pop);
		}
		
		@Test
		@DisplayName("throws EmptyStackException when peeked")
		void throwsExceptionWhenPeeked() {
			assertThrows(EmptyStackException.class, stack::peek);
		}
		
		@Nested
		@DisplayName("after pushing an element")
		class AfterPushing {
			
			String anElement = "an element";
			
			@BeforeEach
			void pushAnElement() {
				stack.push(anElement);
			}
			
			@Test
			@DisplayName("it is no longer empty")
			void isNotEmpty() {
				assertFalse(stack.isEmpty());
			}
			
			@Test
			@DisplayName("returns the element when popped and is empty")
			void returnElementWhenPopped() {
				assertEquals(anElement, stack.pop());
				assertTrue(stack.isEmpty());
			}
			
			@Test
			@DisplayName("returns the element when peeked but remains not empty")
			void returnElementWhenPeeked() {
				assertEquals(anElement, stack.peek());
				assertFalse(stack.isEmpty());
			}
			
			@Test
			@DisplayName("list has a")
			void testLista(){
				assertEquals("a",list.get(0));
			}
		}
	}
}
