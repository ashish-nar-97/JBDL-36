package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DemoConfig {

    private static Logger logger = LoggerFactory.getLogger(DemoConfig.class);

    @Bean
    @Scope("prototype")
    public RestTemplate getTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        logger.info("Inside getTemplate function : {}", restTemplate);
//        return "restTemplate Created";
        return restTemplate;
    }

    @Bean
    public RestTemplate getTemplate2(){
        RestTemplate restTemplate = new RestTemplate();
        logger.info("Inside getTemplate function : {}", restTemplate);
//        return "restTemplate Created";
        return restTemplate;
    }
}
