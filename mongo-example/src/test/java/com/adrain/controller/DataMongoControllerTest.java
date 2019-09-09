package com.adrain.controller;

import com.adrain.repository.CustomerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(DataMongoController.class)
public class DataMongoControllerTest {


	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private MongoTemplate mongoTemplate;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@Test
	public void isMoreThan10_True() throws Exception {
		this.mvc.perform(get("/sonar/11").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("true"))
		;
//		.andExpect(jsonPath("$."));
	}
	
	@Test
	public void isMoreThan10_False() throws Exception {
		this.mvc.perform(get("/sonar/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("false"))
		;
//		.andExpect(jsonPath("$."));
	}
}