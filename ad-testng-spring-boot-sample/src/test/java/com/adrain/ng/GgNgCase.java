package com.adrain.ng;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * @Author AdrainHuang
 * @Date 2019/8/27 23:38
 **/
@SpringBootTest
@Slf4j
public class GgNgCase extends AbstractTestNGSpringContextTests {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Test
	public void test1() {
		List<Map> maps = this.mongoTemplate.find(Query.query(Criteria.where("firstName").is("Alice")), Map.class, "customer");
		Assert.assertNotEquals(0,maps.size());
	}
}
