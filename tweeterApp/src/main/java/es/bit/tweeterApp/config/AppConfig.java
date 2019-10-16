package es.bit.tweeterApp.config;

import es.bit.tweeterApp.driven_adapter.RepoTweets;
import es.bit.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import es.bit.tweeterApp.internal.driver_ports.IServiceTweetsPort;
import es.bit.tweeterApp.driver_adapter.ServiceTweetsPortAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "es.bit.tweeterApp.driven_adapter.RepoPortAdapter")
public class AppConfig {

    @Bean
    public IServiceTweetsPort servicePort(@Autowired IRepoTweetsPort repoPortAdapter){
        return new ServiceTweetsPortAdapter(repoPortAdapter);
    }


}
