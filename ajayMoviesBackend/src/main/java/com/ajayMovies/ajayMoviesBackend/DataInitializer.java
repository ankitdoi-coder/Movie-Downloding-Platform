package com.ajayMovies.ajayMoviesBackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ajayMovies.ajayMoviesBackend.Entity.Admin;
import com.ajayMovies.ajayMoviesBackend.Repository.AdminRepo;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (adminRepo.findByEmail("admin@test.com").isEmpty()) {
            logger.info("Creating default admin user...");
            Admin admin = new Admin();
            admin.setEmail("admin@test.com");
            admin.setPassword(passwordEncoder.encode("password")); // Set your desired password here
            adminRepo.save(admin);
            logger.info("Default admin user created with email 'admin@test.com' and password 'password'");
        }
    }
}