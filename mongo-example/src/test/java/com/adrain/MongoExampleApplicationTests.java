package com.adrain;

import com.adrain.document.Customer;
import com.adrain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
@DataMongoTest
@Import(MongoConfig.class)
@Slf4j
public class MongoExampleApplicationTests {

	private MongoTemplate mongoTemplate;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	public void contextLoads() {
		this.customerRepository.save(new Customer("Tom" ,"Smith"));
		System.out.println(this.customerRepository.findByFirstName("Tom"));
	}

}
