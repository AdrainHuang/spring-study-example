package com.adrain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author AdrainHuang
 * @Date 2019/8/4 12:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestCaseInsert {
	
	@Sql("sql/a.sql")
	@Test
	public void test1(){
		System.out.println("ok");
	}
}
