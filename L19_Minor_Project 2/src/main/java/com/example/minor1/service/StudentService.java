package com.example.minor1.service;

import com.example.minor1.model.MyUser;
import com.example.minor1.model.Student;
import com.example.minor1.repository.StudentRepository;
import com.example.minor1.request.StudentCreateRequest;
import com.example.minor1.request.UserCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    MyUserDetailsService myUserDetailsService;

//    public void create(StudentCreateRequest studentCreateRequest){
//
//        MyUser myUser = MyUser.builder()
//                .authority(studentAuthority)
//                .username(studentCreateRequest.getUsername())
//                .password(studentCreateRequest.getPassword())
//                .build();
//
//        myUser = myUserDetailsService.createUser(myUser);
//
//        Student student = studentCreateRequest.to();
//        student.setMyUser(myUser);
//
//        studentRepository.save(student);
//    }

    public void create(StudentCreateRequest studentCreateRequest){

        UserCreateRequest userCreateRequest = studentCreateRequest.toUser(studentCreateRequest);
        MyUser myUser = myUserDetailsService.createUser(userCreateRequest);

        Student student = studentCreateRequest.to();
        student.setMyUser(myUser);

        studentRepository.save(student);
    }


    public Student findStudentByStudentId(Integer studentId) {

        return studentRepository.findById(studentId).orElse(null);
    }
}
