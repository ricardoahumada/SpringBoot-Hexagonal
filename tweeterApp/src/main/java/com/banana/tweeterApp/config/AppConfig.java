package com.banana.tweeterApp.config;

import com.banana.tweeterApp.driven_adapter.RepoTweets;
import com.banana.tweeterApp.internal.driven_ports.IRepoTweetsPort;
import com.banana.tweeterApp.internal.driver_ports.IServiceTweetsPort;
import com.banana.tweeterApp.driver_adapter.ServiceTweetsPortAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = "com.banana.tweeterApp.driven_adapter.RepoPortAdapter")
public class AppConfig {

    @Bean
    public IServiceTweetsPort servicePort(@Autowired IRepoTweetsPort repoPortAdapter){
        return new ServiceTweetsPortAdapter(repoPortAdapter);
    }


}
