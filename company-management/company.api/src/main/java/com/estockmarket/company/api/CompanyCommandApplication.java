package com.estockmarket.company.api;

import com.estockmarket.company.core.configuration.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
public class CompanyCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyCommandApplication.class, args);
	}

}
