package com.zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        /*SpringApplication.run(App.class,args);*/
        SpringApplication sa = new SpringApplication(App.class);
        sa.run(args);
    }
}
