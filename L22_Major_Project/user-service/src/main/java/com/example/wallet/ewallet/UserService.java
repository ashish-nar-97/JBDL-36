package com.example.wallet.ewallet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public User loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

        return userRepository.findByPhoneNumber(phoneNumber);
    }

    public void create(UserCreateRequest userCreateRequest) throws JsonProcessingException {
        User user = userCreateRequest.to();
        user.setPassword(encryptPassword(user.getPassword()));
        user.setAuthorities(UserConstants.USER_AUTHORITY);

        userRepository.save(user);
        // TODO: publish the event post user creation which can be listened by consumers.

        JSONObject jsonObject = new JSONObject();
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_USERID, user.getId());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_PHONE_NUMBER, user.getPhoneNumber());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_KEY, user.getUserIdentifier());
        jsonObject.put(CommonConstants.USER_CREATION_TOPIC_IDENTIFIER_VALUE, user.getIdentifierValue());

        kafkaTemplate.send(CommonConstants.USER_CREATION_TOPIC, objectMapper.writeValueAsString(jsonObject));

    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    private String encryptPassword(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }
}
