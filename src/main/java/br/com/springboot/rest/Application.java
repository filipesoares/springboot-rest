package br.com.springboot.rest;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.springboot.rest.model.User;
import br.com.springboot.rest.repository.UserRepository;

@SpringBootApplication
@EnableSpringDataWebSupport
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserRepository repository) {
    	
    	final PasswordEncoder encoder = new BCryptPasswordEncoder();
    	final String password = "123456";
    	
        return args -> {
        	repository.save(new User("Jhon Doe", true, "jhon", encoder.encode(password), "jhon@email.com", null, LocalDateTime.now()));
        	repository.save(new User("Sarah James", true, "sarah", encoder.encode(password), "sarah@email.com", null, LocalDateTime.now()));
        	repository.save(new User("Aaron Field", true, "aaron", encoder.encode(password), "aaron@email.com", null, LocalDateTime.now()));
        };
    }

}
