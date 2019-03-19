package com.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@MapperScan(basePackages = "com.demo.mapper")
@SpringBootApplication
public class ServerApplication extends WebMvcConfigurerAdapter {


    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
