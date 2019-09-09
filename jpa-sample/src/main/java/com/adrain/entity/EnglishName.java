package com.adrain.entity;

import javax.persistence.*;

/**
 * @Author AdrainHuang
 * @Date 2019/8/4 12:22
 **/
@Entity(name = "english_name")
public class EnglishName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Column
	private String name;
	
	
	@Column
	private int age;
}
