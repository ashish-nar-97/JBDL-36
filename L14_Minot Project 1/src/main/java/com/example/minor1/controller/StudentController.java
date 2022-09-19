package com.example.minor1.controller;

import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.service.BookService;
import com.example.minor1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }
}
