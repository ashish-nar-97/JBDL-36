package com.example.demosecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/demo")
    public String greet(){
        return "Hello World.";
    }

    @GetMapping("/testcode")
    public String testCode(){
        return "Testing the Code.";
    }

    @GetMapping("/developcode")
    public String developeCode(){
        return "Developing the Code.";
    }

    @GetMapping("/accessserver")
    public String accessServer(){
        return "Accessing the Server.";
    }

    @PostMapping("/home")
    public String home(){
        return "Welcome To Home Page.";
    }

    @GetMapping("/home/all")
    public String homeAll(){
        return "Welcome To Home Page.";
    }

    @PostMapping("/signup")
    public void signUp(@RequestParam("name") String name,
                       @RequestParam("email") String email,
                       @RequestParam("password") String password,
                       @RequestParam("authority") String authority){

        MyUser myUser = MyUser.builder()
                .name(name)
                .email(email)
                .password(passwordEncoder.encode(password))
                .authority(authority)
                .build();

        userDetailsService.signUp(myUser);

    }

    /**
     * user ==> /demo ==> /login ==> entered username and password  ==> /demo
     * */

}
