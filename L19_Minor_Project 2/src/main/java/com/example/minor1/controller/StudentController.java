package com.example.minor1.controller;

import com.example.minor1.model.MyUser;
import com.example.minor1.model.Student;
import com.example.minor1.request.BookCreateRequest;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.service.BookService;
import com.example.minor1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/student")
    public void createStudent(@RequestBody StudentCreateRequest studentCreateRequest){
        studentService.create(studentCreateRequest);
    }

    // Only used by Student.
    @GetMapping("/student")
    public Student getStudent() throws Exception {
        // take the user details from SecurityContext. ==> userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        int studentId = myUser.getStudent().getId();
        if(myUser.getStudent() == null){
            throw new Exception("User requesting the details is not a student.");
        }
        return studentService.findStudentByStudentId(studentId);
    }

    // Only used by Admin.
    @GetMapping("/student-for-admin")
    public Student getStudentForAdmin(@RequestParam("studentId") int studentId) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUser myUser = (MyUser) authentication.getPrincipal();
        if(myUser.getAdmin() == null){
            throw new Exception("User requesting the details is not a Admin.");
        }

        return studentService.findStudentByStudentId(studentId);
    }

}
