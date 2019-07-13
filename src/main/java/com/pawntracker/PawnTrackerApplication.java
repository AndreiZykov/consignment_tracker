package com.pawntracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PawnTrackerApplication extends SpringBootServletInitializer {


    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PawnTrackerApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(PawnTrackerApplication.class, args);
    }


}
