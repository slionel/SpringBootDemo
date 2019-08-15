package com.zz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zsj55
 */
@SpringBootApplication
public class SpringBootThymeleaf {
    public static void main(String[] args) {
        /*SpringApplication.run(SpringBootThymeleaf.class,args);*/
        SpringApplication sa = new SpringApplication(SpringBootThymeleaf.class);
        sa.run(args);
    }
}
