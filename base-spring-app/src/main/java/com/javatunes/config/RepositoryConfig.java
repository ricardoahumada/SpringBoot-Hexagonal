package com.javatunes.config;

import com.javatunes.persistence.InMemoryItemRepository;
import com.javatunes.persistence.ItemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"prod","default"})
public class RepositoryConfig {
    @Bean
    public ItemRepository getItemRepository(){
        System.out.println("In prod....");
        return new InMemoryItemRepository();
    }
}
