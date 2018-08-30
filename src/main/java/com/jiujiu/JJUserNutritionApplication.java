package com.jiujiu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class JJUserNutritionApplication {

	public static void main(String[] args) {
		SpringApplication.run(JJUserNutritionApplication.class, args);
	}
}
