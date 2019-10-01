package es.bit.tareasproyectoshex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.bit.tareasproyectoshex.ports.UserRepositoryPort;
import es.bit.tareasproyectoshex.ports.UserServicePort;
import es.bit.tareasproyectoshex.repositoryadapters.UserRepositoryPortAdapter;
import es.bit.serviceadapters.UserServicePortAdapter;

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
