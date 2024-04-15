package com.dame.uasz;

import java.util.Arrays;

import com.dame.uasz.Model.User;
import com.dame.uasz.Repository.UserRepository;
import com.dame.uasz.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class GestionInsertion implements CommandLineRunner {

	private static final Logger logger = 
			LoggerFactory.getLogger(GestionInsertion.class);

			@Autowired
			private UserRepository userRepository;
			@Autowired
			private UserService userService;
				
			public static void main(String[] args) {
				SpringApplication.run(GestionInsertion.class, args);
			}
		
			@Override
			public void run(String... args) throws Exception {
				// Add owner objects and save these to db

				User user1 = new User(null,"user","user","USER","user@gmail.com",null,null);
				userService.createUser(user1);

				User user2 = new User(null,"resp","resp","RESP","resp@gmail.com",null,null);
				userService.createUser(user2);

				User user3= new User(null,"admin","admin","ADMIN","admin@gmail.com",null,null);
				userService.createUser(user3);
			}

}


