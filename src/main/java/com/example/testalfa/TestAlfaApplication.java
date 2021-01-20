package com.example.testalfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestAlfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAlfaApplication.class, args);
	}

}
