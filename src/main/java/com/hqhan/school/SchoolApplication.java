package com.hqhan.school;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableCaching
public class SchoolApplication implements DisposableBean, CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application start Successfully and do your init business");
    }


    @Override
    public void destroy() throws Exception {
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
