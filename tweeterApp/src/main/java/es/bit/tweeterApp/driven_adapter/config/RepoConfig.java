package es.bit.tweeterApp.driven_adapter.config;

import es.bit.tweeterApp.driven_adapter.RepoTweetsPortAdapter;
import es.bit.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public IRepoTweetsPort getRepoTweetsPort(){
        return new RepoTweetsPortAdapter();
    }
}
