package com.example.minor1;

import com.example.minor1.model.Author;
import com.example.minor1.repository.AuthorRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Minor1Application implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(Minor1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("In run function of main class.");
		List<Author> authorList = authorRepository
				.findByAgeGreaterThanEqualAndCountryAndNameStartingWith(30, "India", "P");
//		authorList.forEach(obj -> System.out.println(obj.getName()));
		authorList.stream()
				.map(author -> author.getBookList())
				.forEach(books -> System.out.println(books.size()));
	}
}
