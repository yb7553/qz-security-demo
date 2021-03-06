package com.qz;
 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
@RestController
public class Application {
	@GetMapping("/hello")
	public String hello() {
		return "hell spring security";
	}

	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	
}
