package com.javatunes.config;

import com.javatunes.persistence.InMemoryItemRepository;
import com.javatunes.persistence.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class RepositoryConfigDev {
    @Bean
    public ItemRepository getItemRepository(){
        System.out.println("In dev....");
        return new InMemoryItemRepository();
    }
}
