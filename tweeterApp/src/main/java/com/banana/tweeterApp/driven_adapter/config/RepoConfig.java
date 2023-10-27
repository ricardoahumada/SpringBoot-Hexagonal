package com.banana.tweeterApp.driven_adapter.config;

import com.banana.tweeterApp.driven_adapter.RepoTweetsPortAdapter;
import com.banana.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepoConfig {
    @Bean
    public IRepoTweetsPort getRepoTweetsPort(){
        return new RepoTweetsPortAdapter();
    }
}
