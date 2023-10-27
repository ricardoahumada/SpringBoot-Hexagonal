package com.banana.tareasproyectoshex.config;

import com.banana.tareasproyectoshex.ports.UserRepository;
import com.banana.tareasproyectoshex.ports.UserService;
import com.banana.tareasproyectoshex.repositoryadapters.UserIMRepositoryAdapter;
import com.banana.tareasproyectoshex.repositoryadapters.UserJPARepositoryAdapter;
import com.banana.tareasproyectoshex.serviceadapters.UserServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:app.properties")
public class ApplicationConfiguration {
    @Autowired
    private Environment env;

    @Bean
    @Autowired
    public UserService userService(@Qualifier("JPARepo") UserRepository userRepository) {
        UserServiceAdapter usa= new UserServiceAdapter();
        usa.setUserRepository(userRepository);
        return usa;
    }

    @Bean(name = "JPARepo")
    public UserRepository userJPARepository(){
        return new UserJPARepositoryAdapter();
    }

    @Bean(name = "IMRepo")
    public UserRepository userIMRepository(){
        return new UserIMRepositoryAdapter();
    }

}
