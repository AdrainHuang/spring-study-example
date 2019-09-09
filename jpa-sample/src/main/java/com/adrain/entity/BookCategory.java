package com.adrain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author AdrainHuang
 * @Date 2019/7/21 23:34
 **/
@Data
@EqualsAndHashCode(exclude = "books")
@Entity
public class BookCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@OneToMany(mappedBy = "bookCategory", cascade = CascadeType.ALL)
	private Set<Book> books;
	
	public BookCategory(String name, Book... books) {
		this.name = name;
		this.books = Stream.of(books).collect(Collectors.toSet());
		this.books.forEach(x -> x.setBookCategory(this));
	}
}
