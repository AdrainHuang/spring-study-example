package com.adrain.controller;

import com.adrain.document.Student;
import com.adrain.document.StudentEnhance;
import com.adrain.util.SequenceUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author AdrainHuang
 * @Date 2019/7/23 23:31
 **/
@RestController
@Slf4j
public class SequenceController {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@GetMapping("/sequenceAutoIncrement")
	public boolean sequenceAutoIncrement(){
		Student student = new Student();
		student.setName("gg");
		mongoTemplate.insert(student);
		return true;
	}
	
	@GetMapping("/getstudent")
	public List<Student> getstudent(){
		List<Student> students = mongoTemplate.find(Query.query(Criteria.where("name").is("gg")), Student.class);
		return students;
	}
	
	@PostMapping("/enhanceAutoIncKey")
	public boolean enhanceAutoIncKey(@RequestBody JSONObject abc){
		mongoTemplate.insert(abc, StudentEnhance.class.getSimpleName());
		return true;
	}
	
	@PostMapping("/utilSequence")
	public JSONObject utilSequence(@RequestBody JSONObject jsonObject){
		jsonObject.put("seqId", SequenceUtil.getNextId("test_seq"));
		JSONObject object = mongoTemplate.insert(jsonObject, "test_seq");
		return object;
	}
}
