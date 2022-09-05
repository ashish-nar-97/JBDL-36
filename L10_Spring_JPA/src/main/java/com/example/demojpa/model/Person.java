package com.example.demojpa.model;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "my_person")
public class Person {

    @Id
    private Integer id;

    @Column(name = "first_name", length = 30)
    private String firstName;

    private String lastName;
    private String dob;
    private Integer age;

    @Transient
    private String countryCode;

}
