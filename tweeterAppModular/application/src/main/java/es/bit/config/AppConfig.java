package es.bit.config;

import es.bit.ServiceTweetsPortAdapter;
import es.bit.internal.driven_ports.IRepoTweetsPort;
import es.bit.internal.driver_ports.IServiceTweetsPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public IServiceTweetsPort servicePort(@Autowired IRepoTweetsPort repoPortAdapter){
        return new ServiceTweetsPortAdapter(repoPortAdapter);
    }


}
