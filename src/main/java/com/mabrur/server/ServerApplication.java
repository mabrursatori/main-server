package com.mabrur.server;

import com.mabrur.server.doa.entities.Category;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}

	@GetMapping
	public String index() {

		

		return "Mabrur Server App";
	}


	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
