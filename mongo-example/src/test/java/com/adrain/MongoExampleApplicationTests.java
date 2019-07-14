package com.adrain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MongoExampleApplicationTests {

	private MongoTemplate mongoTemplate;
	
	@Test
	public void contextLoads() {
	
	}

	public void testSearch(){
	
	}
}
