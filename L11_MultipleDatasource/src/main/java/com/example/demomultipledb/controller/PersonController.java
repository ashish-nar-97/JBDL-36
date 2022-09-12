package com.example.demomultipledb.controller;

import com.example.demomultipledb.exception.PersonNotFoundException;
import com.example.demomultipledb.authordb.Author;
import com.example.demomultipledb.model.CreatePersonRequest;
import com.example.demomultipledb.persondb.Person;
import com.example.demomultipledb.authordb.AuthorRepository;
import com.example.demomultipledb.service.PersonService;
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

    @Autowired
    AuthorRepository authorRepository;

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

    @PostMapping("/author")
    public Author createAuthor(@RequestParam("age") Integer age, @RequestParam("name") String name){
        Author author = Author.builder()
//                .age(age)
                .authorName(name)
                .build();

        return authorRepository.save(author);
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
