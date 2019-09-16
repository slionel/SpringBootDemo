package com.zsj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zsj55
 */
@SpringBootApplication
@MapperScan("com.zsj.mapper")
public class SpringBootAngularJs {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootAngularJs.class,args);
    }
}
