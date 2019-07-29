package es.bit.tareasproyectoshex.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.bit.tareasproyectoshex.ports.UserRepository;
import es.bit.tareasproyectoshex.ports.UserService;
import es.bit.tareasproyectoshex.repositoryadapters.UserRepositoryAdapter;
import es.bit.tareasproyectoshex.serviceadapters.UserServiceAdapter;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository) {
        UserServiceAdapter usa= new UserServiceAdapter();
        usa.setUserRepository(userRepository);
        return usa;
    }

    
    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryAdapter();
    }
}
