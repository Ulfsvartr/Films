package com.tstu.config;


import com.google.gson.Gson;
import controllers.FilmController;
import controllers.UserController;
import org.glassfish.jersey.server.ResourceConfig;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public ModelMapper map() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public ResourceConfig jerseyConfiguration() {
        ResourceConfig resourceConfig = new ResourceConfig();
        resourceConfig.register(FilmController.class);
        resourceConfig.register(UserController.class);
        return resourceConfig;
    }
}
