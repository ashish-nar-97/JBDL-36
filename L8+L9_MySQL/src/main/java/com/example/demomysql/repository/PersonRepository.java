package com.example.demomysql.repository;

import com.example.demomysql.controller.PersonController;
import com.example.demomysql.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonRepository {

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

//    @Autowired
    private Connection connection;

    public PersonRepository(Connection connection) throws SQLException {
        this.connection = connection;
        createTable();

    }

    public void createPerson(Person person) {
        // logic to create the person into the DB.

        // create table person(id int primary key, first_name varchar(30), last_name varchar(30), age int, dob varchar(12));

        // 1. DB Connection.
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql:localhost:3306/jbdl36_person", "root", "password");
            // 2. Write Query.
            PreparedStatement statement = connection.prepareStatement("insert into person(first_name, " +
                    "last_name, age, dob) VALUES(?, ?, ?, ?)");
//            statement.setInt(1, person.getId());
            statement.setString(1, person.getFirstName());
            statement.setString(2, person.getLastName());
            statement.setInt(3, person.getAge());
            statement.setString(4, person.getDob());


            // 3. execute the query.

            int result = statement.executeUpdate();

            logger.info("Insert statement result {}", result >= 1 ? true : false);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createPersonStatic(Person person) {
        // logic to create the person into the DB.

        // create table person(id int primary key, first_name varchar(30), last_name varchar(30), age int, dob varchar(12));

        // 1. DB Connection.
        try {
//            Connection connection = DriverManager.getConnection("jdbc:mysql:localhost:3306/jbdl36_person", "root", "password");
              // 2. Write Query.
            Statement statement = connection.createStatement();

            // 3. execute the query.

            int result = statement.executeUpdate("insert into person(id, first_name, " +
                    "last_name, age, dob) VALUES(1, 'ABC', 'DEF', 20, '2002-01-01')");

            logger.info("Insert statement result {}", result >= 1 ? true : false);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(){
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("create table if not exists person(id int primary key auto_increment, " +
                    "first_name varchar(30), last_name varchar(30), age int, dob varchar(12))");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Person getPersonById(int pId) {

        try{
            PreparedStatement statement = connection.prepareStatement("select * " +
                    "from person where id = ?");
            statement.setInt(1, pId);
            ResultSet personResult = statement.executeQuery();
            ResultSet resultSet = statement.executeQuery("select * from person");
            while(resultSet.next()){
                Person person = getPersonFromResultSet(resultSet);

                return person;
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }


    public List<Person> getPeople() {
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from person");
            List<Person> personList = new ArrayList<>();
            while(resultSet.next()){
                Person person = getPersonFromResultSet(resultSet);
                personList.add(person);
            }
            return personList;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    private Person getPersonFromResultSet(ResultSet resultSet) throws SQLException {
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        int id = resultSet.getInt("id");
        int age = resultSet.getInt(4);
        String dob = resultSet.getString(5);

        Person person = Person.builder()
                .id(id)
                .firstName(first_name)
                .lastName(last_name)
                .age(age)
                .dob(dob)
                .build();

        return person;
    }

    public boolean deleteById(int pId) {
        try{
            PreparedStatement statement = connection.prepareStatement("delete from person where id = ?");

            statement.setInt(1, pId);

            int result = statement.executeUpdate();
            logger.info("Delete statement result {}", result >= 1 ? true : false);

            return result >= 1 ? true : false;

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return false;
    }
}
