package com.adrain.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @Author AdrainHuang
 * @Date 2019/7/18 23:43
 **/
@Document(collection = "customer")
public class Customer {
	
	@Id
	public String id;
	
	@Field("firstname")
	public String firstName;
	@Field("lastname")
	public String lastName;
	
	public Customer() {}
	
	public Customer(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	@Override
	public String toString() {
		return String.format(
		"Customer[id=%s, firstName='%s', lastName='%s']",
		id, firstName, lastName);
	}
	
	
}
