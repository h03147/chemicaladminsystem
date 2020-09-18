package com.cmkjspringboot.chemicalsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ChemicalsystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChemicalsystemApplication.class, args);
    }

    //启动BCrypt加密
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
