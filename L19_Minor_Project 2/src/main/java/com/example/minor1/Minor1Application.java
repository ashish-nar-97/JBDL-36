package com.example.minor1;

import com.example.minor1.model.Admin;
import com.example.minor1.model.Author;
import com.example.minor1.model.MyUser;
import com.example.minor1.repository.AdminRepository;
import com.example.minor1.repository.AuthorRepository;
import com.example.minor1.repository.MyUserRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class Minor1Application implements CommandLineRunner {

	@Autowired
	AuthorRepository authorRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	MyUserRepository myUserRepository;

	@Autowired
	AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(Minor1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("In run function of main class.");

//		MyUser myUser = MyUser.builder()
//				.username("raj")
//				.password(passwordEncoder.encode("raj123"))
//				.authority("adm")
//				.build();
//
//		myUser = myUserRepository.save(myUser);
//		Admin admin = Admin.builder()
//				.name("Raj Shukla")
//				.age(40)
//				.myUser(myUser)
//				.build();
//
//		adminRepository.save(admin);
	}
}
