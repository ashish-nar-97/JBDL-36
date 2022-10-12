package com.example.minor1.service;

import com.example.minor1.model.MyUser;
import com.example.minor1.repository.CacheRepository;
import com.example.minor1.repository.MyUserRepository;
import com.example.minor1.request.UserCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    CacheRepository cacheRepository;

    @Autowired
    MyUserRepository myUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${users.student.authority}")
    String studentAuthority;

    @Value("${users.admin.authority}")
    String adminAuthority;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser = cacheRepository.get(username);
        log.info("Inside loadUserByUsername.");
        if(myUser == null){
            log.info("Data not present in Cache.");
            myUser = myUserRepository.findByUsername(username);
            log.info("Data fetched from DB.");
            if(myUser != null){
                cacheRepository.set(myUser);
            }
        }
        return myUser;
    }

    public MyUser createUser(MyUser myUser) {
        return myUserRepository.save(myUser);
    }

    public MyUser createUser(UserCreateRequest userCreateRequest){
        MyUser.MyUserBuilder myUserBuilder = MyUser.builder()
                .username(userCreateRequest.getUsername())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()));

        if(userCreateRequest.getStudent() != null){
            myUserBuilder.authority(studentAuthority);
        }else{
            myUserBuilder.authority(adminAuthority);
        }

        return myUserRepository.save(myUserBuilder.build());
    }
}
