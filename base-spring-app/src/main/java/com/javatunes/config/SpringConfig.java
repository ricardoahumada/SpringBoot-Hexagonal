package com.javatunes.config;

import com.javatunes.persistence.InMemoryItemRepository;
import com.javatunes.persistence.ItemRepository;
import com.javatunes.service.Catalog;
import com.javatunes.service.CatalogImpl;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = "com.javatunes.config")
//@Import({ServiceConfig.class})
//@ImportResource("classpath:applicationContext.xml")
public class SpringConfig {

}
