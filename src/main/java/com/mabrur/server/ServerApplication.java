package com.mabrur.server;

import com.mabrur.server.doa.entities.Category;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;

@SpringBootApplication
@Controller
public class ServerApplication {

	public static void main(String[] args) {

		SpringApplication.run(ServerApplication.class, args);
	}

	@GetMapping
	public String index(Model model) {

		return "index";
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
