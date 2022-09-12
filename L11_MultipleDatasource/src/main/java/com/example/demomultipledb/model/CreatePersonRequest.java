package com.example.demomultipledb.model;

import com.example.demomultipledb.persondb.Person;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreatePersonRequest {

    @NotBlank(message = "FirstName can not be empty.")
    private String firstName;

    private String lastName;

    @NotBlank(message = "Date of Birth can not be empty.")
    private String dob;

    public Person to(){
        return Person.builder()
                .firstName(firstName)
                .lastName(lastName)
                .dob(dob)
                .build();
    }

}
