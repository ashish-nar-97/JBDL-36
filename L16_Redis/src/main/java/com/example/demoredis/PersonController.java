package com.example.demoredis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class PersonController {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    ObjectMapper objectMapper;

    private static final String PERSON_KEY_PREFIX = "per::";
    private static final String PERSON_LIST_KEY = "per_list::";

    private static final String PERSON_HASH_KEY = "per_hash::";

    /**
     *
     * 1] String Operations.
     * 2] List Operations.
     * 3] Set Operations.
     * 4] Hash Operations.
     */

    // String Operations.

    @PostMapping("/string/person")
    public void savePerson(@RequestBody Person person){

        String key = getKey(person.getId());
        // 1 akashay
        // key : per::1
        // per::2
        // per::3

        // keys per::*

        redisTemplate.opsForValue().set(key, person);
    }

    @GetMapping("/string/person")
    public Person getPerson(@RequestParam("id") long personId){

        return (Person) redisTemplate.opsForValue().get(getKey(personId));
    }

    // List Operations :

    @PostMapping("/lpush/person")
    public void lpush(@RequestBody Person person){
        redisTemplate.opsForList().leftPush(PERSON_LIST_KEY, person);
    }

    @PostMapping("/rpush/person")
    public void rpush(@RequestBody Person person){
        redisTemplate.opsForList().rightPush(PERSON_LIST_KEY, person);
    }

    @DeleteMapping("/lpop/person")
    public Person lpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        return (Person) redisTemplate.opsForList().leftPop(PERSON_LIST_KEY);
    }

    @DeleteMapping("/rpop/person")
    public Person rpop(@RequestParam(value = "count", required = false, defaultValue = "1") int count){
        return (Person) redisTemplate.opsForList().rightPop(PERSON_LIST_KEY);
    }

    @GetMapping("/lrange/person")
    public List<Person> lrange(@RequestParam(value = "start", required = false, defaultValue = "0") int start,
                               @RequestParam(value = "end", required = false, defaultValue = "-1") int end){
        return redisTemplate.opsForList().range(PERSON_LIST_KEY, start, end)
                .stream().map(person -> (Person) person)
                .collect(Collectors.toList());
    }

    // Set Operations : HomeWork

    // Hashes Operations :

    @PostMapping("/hash/person")
    public void savePersonInHash(@RequestBody List<Person> personList){

        personList.stream()
                .filter(person -> person.getId() != 0)
                .forEach(person -> {
                    Map map = objectMapper.convertValue(person, Map.class);
                    redisTemplate.opsForHash().putAll(getHashKey(person.getId()), map);
                });
    }

    @GetMapping("/hash/person/all")
    public List<Person> getPerson(@RequestParam("ids") List<Long> ids){

        return ids.stream().map(id -> redisTemplate.opsForHash().entries(getHashKey(id)))
                .map(entryMap -> objectMapper.convertValue(entryMap, Person.class))
                .collect(Collectors.toList());
    }

    private String getHashKey(long id){
        return PERSON_HASH_KEY + id; //per_hash::1, per_hash::2, per_hash::3
    }





    private String getKey(long personId){
        return PERSON_KEY_PREFIX + personId;
    }
}
