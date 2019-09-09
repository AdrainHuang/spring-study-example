package com.adrain.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author AdrainHuang
 * @Date 2019/7/14 16:35
 **/
@RestController
@Slf4j
public class DataMongoController {
	
	
	public static final String COLLECTION = "jtest";
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@GetMapping("/sonar/{b}")
	public boolean isMoreThan10(@PathVariable("b") Integer b) {
		return b > 10;
	}
	
//	@PostMapping("/json")
//	public JSONObject create(@RequestBody JSONObject jsonObject) {
//		log.info("coming :{}", jsonObject.toString());
//		Document parse = Document.parse(jsonObject.toString());
//		mongoTemplate.insert(jsonObject, COLLECTION);
//		return jsonObject;
//	}
//
//	@DeleteMapping("/json/kk/{kk}")
//	public Long remove(@PathVariable("kk") String id) {
//		Query q = new Query();
//		q.addCriteria(Criteria.where("kk").is(id));
//		DeleteResult remove = mongoTemplate.remove(q, COLLECTION);
//		return remove.getDeletedCount();
//	}
//
//	@DeleteMapping("/json/jtests")
//	public Long remove() {
//		JSONObject j = new JSONObject();
//		DeleteResult remove = mongoTemplate.remove(j);
//		return remove.getDeletedCount();
//	}
//
//	@GetMapping("/mother/{name}")
//	public JSONObject getkkMother(@PathVariable("name") String name) {
//		final JSONObject[] jsonObject = new JSONObject[1];
//		mongoTemplate.executeQuery(Query.query(Criteria.where("kkmother.name").is(name)), COLLECTION, new DocumentCallbackHandler() {
//			@Override
//			public void processDocument(Document document) throws MongoException, DataAccessException {
//				jsonObject[0] = JSONObject.parseObject(document.toJson());
//			}
//		});
//		return jsonObject[0];
//	}
//
//	@PostMapping("json/maping")
//	public Map createbyMap(@RequestBody Map map) {
//		log.info("coming :{}", map);
//		mongoTemplate.insert(map, COLLECTION);
//		return map;
//	}
//
//	@Autowired
//	private CustomerRepository repository;
//
//	/**
//	 * mongo 的普通操作
//	 *
//	 * @return
//	 */
//	@GetMapping("/mongo/save")
//	public boolean saveMongo() {
//		repository.save(new Customer("Alice", "Smith"));
//		repository.save(new Customer("Bob", "Smith"));
//
//		// fetch all customers
//		System.out.println("Customers found with findAll():");
//		System.out.println("-------------------------------");
//		for (Customer customer : repository.findAll()) {
//			System.out.println(customer);
//		}
//		System.out.println();
//
//		// fetch an individual customer
//		System.out.println("Customer found with findByFirstName('Alice'):");
//		System.out.println("--------------------------------");
//		System.out.println(repository.findByFirstName("Alice"));
//
//		System.out.println("Customers found with findByLastName('Smith'):");
//		System.out.println("--------------------------------");
//		for (Customer customer : repository.findByLastName("Smith")) {
//			System.out.println(customer);
//		}
//		return true;
//	}
//
//
//	@GetMapping("/mm")
//	public void mmColection() {
//		Query query = Query.query(
//		new Criteria().orOperator(
//		Criteria.where("age").regex("1"),
//		Criteria.where("name").regex("3")
//		)
//		);
//
//		mongoTemplate.executeQuery(query, "mm", new DocumentCallbackHandler() {
//			@Override
//			public void processDocument(Document document) throws MongoException, DataAccessException {
//				System.out.println(document.toJson());
//			}
//		});
//	}
//

}
