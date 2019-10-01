/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */
 
package es.bit.tareasproyectoshex.configuration;

import es.bit.tareasproyectoshex.ports.UserRepository;
import es.bit.tareasproyectoshex.repositoryadapters.UserIMRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringRepositoryConfig {
	//@Bean(name = "InMemoryRepo")
	public UserRepository userRepository() {
		return new UserIMRepositoryAdapter();
	}

}