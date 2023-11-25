package com.salesianos.triana.appbike;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(info =
@Info(description = "Una App para gestionar el alquiler de bicicletas",
		version = "1.0",
		contact = @Contact(email = "alexanderluquehoffrogge@gmail.com", name = "FullStackDeveloper By Salesianos Triana"),
		license = @License(name = "BikeApp"),
		title = "App de alquiler de bicicletas"
)
)
public class AppbikeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppbikeApplication.class, args);
	}

}
