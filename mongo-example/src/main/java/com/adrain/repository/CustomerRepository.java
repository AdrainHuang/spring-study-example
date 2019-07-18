package com.adrain.repository;

import com.adrain.document.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Author AdrainHuang
 * @Date 2019/7/18 23:47
 **/
public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	public Customer findByFirstName(String firstName);
	public List<Customer> findByLastName(String lastName);
	
}
