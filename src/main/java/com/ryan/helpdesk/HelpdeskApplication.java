package com.ryan.helpdesk;

import com.ryan.helpdesk.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	private DBService dbService;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dbService.instanciaBaseDeDados();
	}

}


