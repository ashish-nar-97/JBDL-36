package com.example.minor1.request;

import com.example.minor1.model.AccountStatus;
import com.example.minor1.model.Student;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    private String address;

    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Student to() {

        return Student.builder()
                .address(address)
                .email(email)
                .contact(contact)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public UserCreateRequest toUser(StudentCreateRequest studentCreateRequest){
        return UserCreateRequest.builder()
                .username(studentCreateRequest.username)
                .password(studentCreateRequest.password)
                .student(to())
                .build();
    }
}
