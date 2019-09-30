package com.javatunes.config;

import com.javatunes.persistence.ItemRepository;
import com.javatunes.service.Catalog;
import com.javatunes.service.CatalogImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ServiceConfig {

    @Autowired
    ItemRepository itemRep;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Catalog getCatalog(){
        return new CatalogImpl(itemRep);
    }
}
