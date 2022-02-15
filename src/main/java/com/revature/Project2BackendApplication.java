package com.revature;

import com.revature.utils.Encryptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Project2BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project2BackendApplication.class, args);

		String testString = "Hello, World";
		String encryptedString = Encryptor.encodePassword(testString);


	}

}
