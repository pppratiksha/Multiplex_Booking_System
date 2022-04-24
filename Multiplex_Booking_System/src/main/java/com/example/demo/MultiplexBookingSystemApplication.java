package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
@EnableSwagger2WebMvc
@SpringBootApplication()
public class MultiplexBookingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiplexBookingSystemApplication.class, args);
	}

}