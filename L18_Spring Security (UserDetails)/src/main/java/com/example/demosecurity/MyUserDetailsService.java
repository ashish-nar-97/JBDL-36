package com.example.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserDetailsRepository myUserDetailsRepository;

    @Autowired
    MongoUserRepository mongoUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // logic for fetching the user details from DB table (Entity).
        return myUserDetailsRepository.findByEmail(username);
        /**
         * Rahul : 123
         * Rahul : 456
         * Rahul : xyz
         *
         * return :
         * 123
         * -----
         * xyz
         * */

    }

    public void signUp(MyUser user){
        myUserDetailsRepository.save(user);
    }
}
