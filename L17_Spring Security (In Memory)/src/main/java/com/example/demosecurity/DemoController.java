package com.example.demosecurity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

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

    @GetMapping("/home")
    public String home(){
        return "Welcome To Home Page.";
    }

    @GetMapping("/home/all")
    public String homeAll(){
        return "Welcome To Home Page.";
    }

    /**
     * user ==> /demo ==> /login ==> entered username and password  ==> /demo
     * */

}
