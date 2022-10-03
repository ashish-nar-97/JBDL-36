package com.example.demosecurity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class DemoSecurityApplication implements CommandLineRunner {

    @Autowired
    MyUserDetailsRepository myUserDetailsRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        SpringApplication.run(DemoSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        MyUser myUser1 = MyUser.builder()
                .name("Sai")
                .email("sai@gmail.com")
                .password(passwordEncoder.encode("sai123"))
                .authority("dev")
                .build();

        MyUser myUser2 = MyUser.builder()
                .name("Rahul")
                .email("rahul@gmail.com")
                .password(passwordEncoder.encode("rahul123"))
                .authority("qa")
                .build();

        MyUser myUser3 = MyUser.builder()
                .name("Rahul")
                .email("rahul@yahoo.in")
                .password(passwordEncoder.encode("rahul*"))
                .authority("qa")
                .build();

        try {
            myUserDetailsRepository.save(myUser1);
            myUserDetailsRepository.save(myUser2);
            myUserDetailsRepository.save(myUser3);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
