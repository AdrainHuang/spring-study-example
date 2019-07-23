package com.adrain.controller;

import com.adrain.document.Customer;
import com.adrain.repository.CustomerRepository;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.MongoException;
import com.mongodb.client.result.DeleteResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author AdrainHuang
 * @Date 2019/7/14 16:35
 **/
@RestController
@Slf4j
public class DataMongoController {
	
	
	public static final String COLLECTION ="jtest";
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@PostMapping("/json")
	public JSONObject create(@RequestBody JSONObject jsonObject){
		log.info("coming :{}", jsonObject.toString());
		Document parse = Document.parse(jsonObject.toString());
		mongoTemplate.insert(jsonObject, COLLECTION);
		return jsonObject;
	}
	
	@DeleteMapping("/json/kk/{kk}")
	public Long remove(@PathVariable("kk") String id){
		Query q = new Query();
		q.addCriteria(Criteria.where("kk").is(id));
		DeleteResult remove = mongoTemplate.remove(q, COLLECTION);
		return remove.getDeletedCount();
	}
	
	@DeleteMapping("/json/jtests")
	public Long remove(){
		JSONObject j = new JSONObject();
		DeleteResult remove = mongoTemplate.remove(j);
		return remove.getDeletedCount();
	}
	
	@GetMapping("/mother/{name}")
	public JSONObject getkkMother(@PathVariable("name") String name){
		final JSONObject[] jsonObject = new JSONObject[1];
		mongoTemplate.executeQuery(Query.query(Criteria.where("kkmother.name").is(name)), COLLECTION, new DocumentCallbackHandler(){
			@Override
			public void processDocument(Document document) throws MongoException, DataAccessException {
				jsonObject[0] = JSONObject.parseObject(document.toJson());
			}
		});
		return jsonObject[0];
	}
	
	@PostMapping("json/maping")
	public Map createbyMap(@RequestBody Map map){
		log.info("coming :{}", map);
		mongoTemplate.insert(map, COLLECTION);
		return map;
	}
	
	@Autowired
	private CustomerRepository repository;
	
	/**
	 * mongo 的普通操作
	 * @return
	 */
	@GetMapping("/mongo/save")
	public boolean saveMongo(){
		repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();
		
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));
		
		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
		return true;
	}
	
}
