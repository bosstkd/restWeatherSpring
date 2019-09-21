package com.react.meteo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MeteoApplication {

	public static void main(String[] args) {
		
        ApplicationContext ctx = SpringApplication.run(MeteoApplication.class, args);
		
		
	}

}
