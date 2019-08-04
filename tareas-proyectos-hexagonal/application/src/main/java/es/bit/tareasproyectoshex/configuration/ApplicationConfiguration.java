package es.bit.tareasproyectoshex.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import es.bit.tareasproyectoshex.ports.UserRepository;
import es.bit.tareasproyectoshex.ports.UserService;
import es.bit.tareasproyectoshex.serviceadapters.UserServiceAdapter;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    @Qualifier("jpaRepo")
    UserRepository userRepository;

    @Bean
    public UserService userService() {
        UserServiceAdapter usa= new UserServiceAdapter();
        usa.setUserRepository(userRepository);
        return usa;
    }

}
