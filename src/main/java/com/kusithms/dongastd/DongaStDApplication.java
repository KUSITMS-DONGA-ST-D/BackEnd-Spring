package com.kusithms.dongastd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DongaStDApplication {


	public static void main(String[] args) {
		SpringApplication.run(DongaStDApplication.class, args);
	}

}
