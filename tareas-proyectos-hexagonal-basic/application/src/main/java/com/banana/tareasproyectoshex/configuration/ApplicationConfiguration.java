package com.banana.tareasproyectoshex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.banana.tareasproyectoshex.ports.UserRepositoryPort;
import com.banana.tareasproyectoshex.ports.UserServicePort;
import com.banana.tareasproyectoshex.repositoryadapters.UserRepositoryPortAdapter;
import com.banana.serviceadapters.UserServicePortAdapter;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserServicePort userService(UserRepositoryPort userRepositoryPort) {
        UserServicePortAdapter usa= new UserServicePortAdapter();
        usa.setUserRepository(userRepositoryPort);
        return usa;
    }

    
    @Bean
    public UserRepositoryPort userRepository() {
        return new UserRepositoryPortAdapter();
    }
}
