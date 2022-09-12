package com.example.demomultipledb.service;

import com.example.demomultipledb.exception.PersonNotFoundException;
import com.example.demomultipledb.model.CreatePersonRequest;
import com.example.demomultipledb.persondb.Person;
import com.example.demomultipledb.persondb.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Random;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public void createPerson(CreatePersonRequest createPersonRequest) {

        Person person = createPersonRequest.to();
        if(person.getAge() == null){
            Integer age = calculateAgeFromDOB(person.getDob());
            person.setAge(age);
        }
        person.setId(new Random().nextInt());

        personRepository.save(person);
    }

    private Integer calculateAgeFromDOB(String dob){
        if(dob == null){
            return null;
        }

        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();

        return Period.between(dobDate, currentDate).getYears();
    }

    public Person getPerson(int id) throws PersonNotFoundException {

//        Optional<Person> personOptional = personRepository.findById(id);
//
//        if(personOptional.isPresent()){
//            return personOptional.get();
//        }

//        return personRepository.findById(id).orElse(null);

        return personRepository.findById(id).orElseThrow(() ->
                new PersonNotFoundException("Person with Id "+id+" Not Found"));
    }

    public List<Person> getPeople() {

        return personRepository.findAll();
    }
}
