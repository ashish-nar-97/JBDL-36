package com.example.demojpa.controller;

import com.example.demojpa.exception.PersonNotFoundException;
import com.example.demojpa.model.CreatePersonRequest;
import com.example.demojpa.model.Person;
import com.example.demojpa.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @PostMapping("/person")
    public void createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
        logger.info("CreatePersonRequest : {}", createPersonRequest);
        logger.info("Saving information into DB");

        personService.createPerson(createPersonRequest);

    }

    @GetMapping("/person")
    public Person getPersonById(@RequestParam("id") int id) throws PersonNotFoundException {

        return personService.getPerson(id);

    }

    @GetMapping("/person/all")
    public List<Person> getAllPersons(){

        return personService.getPeople();

    }

//    @DeleteMapping("/person/{id}")
//    public Person deletePerson(@PathVariable("id") int id) throws Exception {
//        return personService.deleteById(id);
//    }

//    public static void main(String[] args) {
//
//        Person person = new Person();
//        person.setFirstName("XYZ");
//        person.setLastName("ABC");
//        person.setAge(10);
//        person.setId(1);
//
//        Person p = Person.builder()
//                .firstName("XYZ")
//                .lastName("ABC")
//                .age(10)
//                .id(1)
//                .build();
//
//        logger.info("Person {}",p);
//
//    }
}
