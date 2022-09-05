package com.example.demomysql.service;

import com.example.demomysql.BadPersonRequestException;
import com.example.demomysql.model.CreatePersonRequest;
import com.example.demomysql.model.Person;
import com.example.demomysql.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PersonService {


    @Autowired
    PersonRepository personRepository;

    public void createPerson(CreatePersonRequest createPersonRequest) {

        Person person = createPersonRequest.to();

        if(person.getAge() == null){
            Integer age = calculateAgeFromDOB(person.getDob());
            person.setAge(age);
            personRepository.createPerson(person);
        }

    }

    private Integer calculateAgeFromDOB(String dob) {

        if(dob == null){
            return null;
        }
        LocalDate dobDate = LocalDate.parse(dob);
        LocalDate currentDate = LocalDate.now();

        return Period.between(dobDate, currentDate).getYears();
    }

    public Person getPerson(int id) {

        return personRepository.getPersonById(id);
    }

    public List<Person> getPeople(){
        return personRepository.getPeople();
    }

    public Person deleteById(int id) throws Exception {

        Person person = personRepository.getPersonById(id);

        boolean isDeleted = personRepository.deleteById(id);

        if(isDeleted){
            return person;
        }

        throw new BadPersonRequestException("user id does not exists.");
    }
}
