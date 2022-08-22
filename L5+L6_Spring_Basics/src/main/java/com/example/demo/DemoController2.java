package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v2")
public class DemoController2 {

    @Autowired
    Demo demo;

    @Autowired
    @Qualifier("getTemplate")
    RestTemplate restTemplate;

//    @Autowired
//    DemoConfig demoConfig;

    private static Logger logger = LoggerFactory.getLogger(DemoController.class);

    @GetMapping("/sample2")
    public Demo getDemo(){
        logger.info("creating object for DemoController {}",this);
        logger.info("demo in DemoController2 : {}", demo);
        this.demo = demo;
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


// sample1 API : com.example.demo.Demo@2dc76702
// sample2 API : com.example.demo.Demo@2dc76702

// after prototype :

// sample1 API :
// sample2 API :


// API 1 : org.springframework.web.client.RestTemplate@3fe46690
// API 2 : org.springframework.web.client.RestTemplate@3fe46690


// API1 : RestTemplate@317e9c3c
// API2 : RestTemplate@317e9c3c

// after bean as prototype :

// /v2/sample

// API1 : RestTemplate@1442f788
// API2 : RestTemplate@1442f788


// /v1/sample :

// RestTemplate@2c7a8af2
// RestTemplate@2c7a8af2