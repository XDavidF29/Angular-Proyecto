package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entidades.Mascota;

//@SpringBootApplication
@SuppressWarnings("unused")
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public Mascota mascota() {
		return new Mascota("pepe",
				"chiquita",
				1,
				12.3f,
				"/images/eldoggy.jpg",
				"ninguna",
				"bien");
	}

}
