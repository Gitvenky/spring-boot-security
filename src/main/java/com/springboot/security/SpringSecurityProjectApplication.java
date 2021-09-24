package com.springboot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.springboot")
public class SpringSecurityProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityProjectApplication.class, args);
	}

}
