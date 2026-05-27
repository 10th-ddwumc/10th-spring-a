package com.example.umc10th_a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Umc10thAApplication {

    public static void main(String[] args) {

        SpringApplication.run(Umc10thAApplication.class, args);
    }

}
