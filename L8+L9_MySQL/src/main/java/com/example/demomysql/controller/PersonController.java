package com.example.demomysql.controller;

import com.example.demomysql.model.CreatePersonRequest;
import com.example.demomysql.model.Person;
import com.example.demomysql.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
public class PersonController {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

//    @PostMapping("/person")
//    public void createPerson(@RequestBody Person person){
//
//    }

//    @PostMapping("/person")
//    public void createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
//        logger.info("CreatePersonRequest : {}", createPersonRequest);
////        if(createPersonRequest.getFirstName() == null || createPersonRequest.getFirstName().isEmpty()){
////            logger.info("FirstName can not be empty");
////            return;
////        }
////        if(createPersonRequest.getLastName() == null || createPersonRequest.getLastName().isEmpty()){
////            logger.info("LastName can not be empty");
////            return;
////        }
////        if(createPersonRequest.getDob() == null || createPersonRequest.getDob().isEmpty()){
////            logger.info("DOB can not be empty");
////            return;
////        }
//        logger.info("Saving information into DB");
//
//    }

//    @PostMapping("/person")
//    public ResponseEntity createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
//        logger.info("CreatePersonRequest : {}", createPersonRequest);
//
//        Integer a = new Random().nextInt() % 2;
//
////        if(a == 0){
////            logger.info("Random No. % 2 = 0");
////            throw new IndexOutOfBoundsException();
////        }
//
////        if(createPersonRequest.getFirstName() == null || createPersonRequest.getFirstName().isEmpty()){
////            logger.info("FirstName can not be empty");
////            return;
////        }
////        if(createPersonRequest.getLastName() == null || createPersonRequest.getLastName().isEmpty()){
////            logger.info("LastName can not be empty");
////            return;
////        }
////        if(createPersonRequest.getDob() == null || createPersonRequest.getDob().isEmpty()){
////            logger.info("DOB can not be empty");
////            return;
////        }
//        logger.info("Saving information into DB");
//
//        MultiValueMap<String, String> headers = new HttpHeaders();
//        headers.add("sample-response-header", "Person-Response-Type");
//
//        return new ResponseEntity<>(new Person(), headers, HttpStatus.CREATED);
//
//    }

    @PostMapping("/person")
    public void createPerson(@Valid @RequestBody CreatePersonRequest createPersonRequest){
        logger.info("CreatePersonRequest : {}", createPersonRequest);
        logger.info("Saving information into DB");

        personService.createPerson(createPersonRequest);

    }

    @GetMapping("/person")
    public Person getPersonById(@RequestParam("id") int id){

        return personService.getPerson(id);

    }

    @GetMapping("/person/all")
    public List<Person> getPersonById(){

        return personService.getPeople();

    }

    @DeleteMapping("/person/{id}")
    public Person deletePerson(@PathVariable("id") int id) throws Exception {
        return personService.deleteById(id);
    }

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
