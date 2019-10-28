package es.bit;

import es.bit.internal.driven_ports.IRepoTweetsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public IRepoTweetsPort getRepoTweetsPort(){
        return new RepoTweetsPortAdapter();
    }
}
