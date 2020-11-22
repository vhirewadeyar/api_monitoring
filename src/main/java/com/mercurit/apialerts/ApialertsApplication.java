package com.mercurit.apialerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.mercurit.apialerts.domain"})
public class ApialertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApialertsApplication.class, args);
	}

}
