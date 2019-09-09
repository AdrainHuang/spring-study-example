package com.adrain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @Author AdrainHuang
 * @Date 2019/7/21 23:31
 **/
@Data
@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn
	private BookCategory bookCategory;
	
	public Book(String name) {
		this.name = name;
	}
}
