package com.example.minor1.service;

import com.example.minor1.model.Student;
import com.example.minor1.repository.StudentRepository;
import com.example.minor1.request.StudentCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public void create(StudentCreateRequest studentCreateRequest){
        Student student = studentCreateRequest.to();
        studentRepository.save(student);
    }


    public Student findStudentByStudentId(Integer studentId) {

        return studentRepository.findById(studentId).orElse(null);
    }
}
