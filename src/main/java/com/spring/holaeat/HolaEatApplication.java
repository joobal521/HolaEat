package com.spring.holaeat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HolaEatApplication {

    public static void main(String[] args) {
        SpringApplication.run(HolaEatApplication.class, args);
    }

}
