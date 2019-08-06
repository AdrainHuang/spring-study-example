package com.adrain;

import com.adrain.annotation.Script;
import com.adrain.nojdbc.MongoScriptsTestExecutionListener;
import com.adrain.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringRunner.class)
@DataMongoTest
@Slf4j
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
MongoScriptsTestExecutionListener.class})
public class MongoExampleApplicationTests {

	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Test
	@Script("mongoscript/a.js")
	public void scripta() {
		Assert.assertNotNull(this.customerRepository.findByFirstName("ad"));
	}
	
//	@Test
//	@Script("mongoscript/b.js")
//	public void scriptb() {
//		this.customerRepository.save(new Customer("Tom" ,"Smith"));
//		System.out.println(this.customerRepository.findByFirstName("Tom"));
//	}
}
