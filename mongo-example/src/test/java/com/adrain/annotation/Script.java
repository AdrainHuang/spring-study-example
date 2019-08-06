package com.adrain.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Script {
	
	@AliasFor("scripts")
	String[] value() default {};
	
	@AliasFor("value")
	String[] scripts() default {};
	
	String[] statements() default {};
	
	Script.ExecutionPhase executionPhase() default Script.ExecutionPhase.BEFORE_TEST_METHOD;
	
	
	/**
	 * Enumeration of <em>phases</em> that dictate when SQL scripts are executed.
	 */
	enum ExecutionPhase {
		
		/**
		 * The configured SQL scripts and statements will be executed
		 * <em>before</em> the corresponding test method.
		 */
		BEFORE_TEST_METHOD,
		
		/**
		 * The configured SQL scripts and statements will be executed
		 * <em>after</em> the corresponding test method.
		 */
		AFTER_TEST_METHOD
	}
	
}
