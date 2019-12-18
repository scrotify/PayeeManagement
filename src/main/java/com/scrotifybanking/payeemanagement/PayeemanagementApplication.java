package com.scrotifybanking.payeemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class PayeemanagementApplication {

	public static void main(String[] args) {//NOSONAR
		SpringApplication.run(PayeemanagementApplication.class, args);
	}

}
