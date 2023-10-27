package com.banana.config;

import com.banana.ServiceTweetsPortAdapter;
import com.banana.internal.driven_ports.IRepoTweetsPort;
import com.banana.internal.driver_ports.IServiceTweetsPort;
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
