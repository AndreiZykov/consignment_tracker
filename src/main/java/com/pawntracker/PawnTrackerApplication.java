package com.pawntracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class PawnTrackerApplication extends SpringBootServletInitializer {


    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PawnTrackerApplication.class);

    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PawnTrackerApplication.class, args);
    }


}
