package com.example.demomysql.repository;

import com.example.demomysql.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepository {

    public void createPerson(Person person) {
        // logic to create the person into the DB.
    }
}
