package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@RestController
//@Controller
@RestController
@RequestMapping("/v1")
public class DemoController {

    @Autowired
    Demo demo;

    @Autowired
    @Qualifier("getTemplate")
    RestTemplate restTemplate;

//    @Value("${server.port}")
//    int server = 2;

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

//    @Autowired
//    public DemoController(Demo demo){
//        logger.info("creating object for DemoController {}",this);
//        logger.info("demo in DemoController : {}", demo);
//        this.demo = demo;
//    }

//    public DemoController(int a){
//        logger.info("Printing a in DemoController : {}",a);
//    }

//    public DemoController(@Value("${server.port}") int a){
//        logger.info("Server port value in constructor : {}", a);
//    }

    @GetMapping("/sample")
    public Demo getDemo(){
//        Demo demo = new Demo();
        logger.info("demo response : "+demo);
//        DemoController obj1 = new DemoController(10);
//        logger.info("Server is running on port : {}",server);
        return demo;
    }

    @PostMapping("/post-sample")
    public Demo postDemo(){
        logger.info("demo response : "+demo);
        return demo;
    }

    @GetMapping("/getTemplate")
    public void getTemplate(){
//        RestTemplate restTemplate = demoConfig.getTemplate();
//        RestTemplate restTemplate2 = demoConfig.getTemplate2();
        logger.info("RestTemplate is {}", restTemplate);
//        logger.info("RestTemplate2 is {}", restTemplate2);
    }
}

// object created by spring :        com.example.demo.DemoController@52169758
// object created with new keyword : com.example.demo.DemoController@6fbb4061


// API 1st call : demo response : com.example.demo.Demo@10e694d9
// API 2nd call : demo response : com.example.demo.Demo@375d3795

// API call 1: demo response : com.example.demo.Demo@17c47814
// API CALL 2: demo response : com.example.demo.Demo@29569f5c
