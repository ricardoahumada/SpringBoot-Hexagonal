package es.bit.tareasproyectoshex.config;

import es.bit.tareasproyectoshex.ports.UserRepository;
import es.bit.tareasproyectoshex.ports.UserService;
import es.bit.tareasproyectoshex.repositoryadapters.UserIMRepositoryAdapter;
import es.bit.tareasproyectoshex.repositoryadapters.UserJPARepositoryAdapter;
import es.bit.tareasproyectoshex.serviceadapters.UserServiceAdapter;
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
