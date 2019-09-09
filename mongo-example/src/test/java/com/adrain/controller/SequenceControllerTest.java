package com.adrain.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SequenceController.class)
public class SequenceControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MongoTemplate mongoTemplate;

	@Test
	public void getstudent() throws Exception {
		this.mvc.perform(get("/getstudent").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}


}