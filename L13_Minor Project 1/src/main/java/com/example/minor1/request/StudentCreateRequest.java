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

    public Student to() {

        return Student.builder()
                .address(address)
                .email(email)
                .contact(contact)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }
}
