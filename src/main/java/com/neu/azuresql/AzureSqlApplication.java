package com.neu.azuresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
@EnableSwagger2WebMvc
@EnableWebMvc
public class AzureSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(AzureSqlApplication.class, args);
    }

}