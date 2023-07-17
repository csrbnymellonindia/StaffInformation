package com.codeusingjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@ComponentScan({"com.codeusingjava.controller"})
@ComponentScan({"com.codeusingjava.service"})
@EntityScan("com.codeusingjava.model")
@EnableJpaRepositories("com.codeusingjava.repository")
@Controller
@SpringBootApplication
public class SpringJwtApplication implements ErrorController {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}
	
	private static final String PATH = "/error";

	@RequestMapping(value = PATH)
	public String error(){
		return "forward:/";
	}
	@Override
	public String getErrorPath() {
		return PATH;
	}

}
