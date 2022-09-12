package com.example.demomultipledb.persondb;


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

    // Auto : Hibernate will generate your id.

    // Identity : Underlying datasource / database will generate your id.

    // AUTO :
    // Create Statement : create table person(id int primary key) VALUES(<ID>, <NAME>, .....)
    // Insert Statement : insert into person(id, name, ...) VALUES(<ID>, <NAME>, ....);

    // Identity :
    // create statement : create table person(id int primary key auto increment) VALUES(<ID>, <NAME>, .....)
    // insert statement : insert into person(name, ...) VALUES(<NAME>, ....);


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 30, unique = true)
    private String firstName;

    private String lastName;
    private String dob;
    private Integer age;

    @Transient
    private String countryCode;

}
