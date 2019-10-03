/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package es.bit.tareasproyectoshex.repositoryadapters;

import configuration.SpringRepositoryBeanConfig;
import es.bit.tareasproyectoshex.config.SpringRepositoryConfig;
import es.bit.tareasproyectoshex.models.User;
import es.bit.tareasproyectoshex.ports.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={SpringRepositoryBeanConfig.class, SpringRepositoryConfig.class})
public class UserJPARespositoryAdapterTest {

    @Autowired
	@Qualifier("jpaRepo")
	UserRepository repo;
    
	@Test
	public void testFindPositive() {
		User aUser = repo.findOne(1L);
		assertNotNull(aUser);
		System.out.println("Found user:" + aUser);
	}

	@Test
	public void testFindAllPositive() {
		Collection<User> users = repo.findAll();
		assertNotNull(users);
		System.out.println("Found users:" + users);
	}

	@Test
	public void testAddPositive() {
		User user = new User(null,"Nuevo User","e@.com","passx");
		repo.save(user);
		assertNotNull(user);
		System.out.println("Saved user:" + user);
	}

}
