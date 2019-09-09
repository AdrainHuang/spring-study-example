package com.adrain;

import com.adrain.entity.Book;
import com.adrain.entity.BookCategory;
import com.adrain.repository.BookCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JpaExampleApplication implements CommandLineRunner {
	
	@Autowired
	private BookCategoryRepository bookCategoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaExampleApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		// Create a couple of Book and BookCategory
		bookCategoryRepository.save(new BookCategory("Category 1", new Book("Hello Koding 1"), new Book("Hello Koding 2")));
	}
}
