package com.zancheema.pantry;

import com.zancheema.pantry.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class PantryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PantryApplication.class, args);
	}

}
