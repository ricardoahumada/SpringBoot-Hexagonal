/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */
 
package com.javatunes.service;

import static org.junit.Assert.*;

import com.javatunes.config.SpringConfig;
import com.javatunes.persistence.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@ActiveProfiles("dev")
public class CatalogTest {

	@Autowired
	private ApplicationContext ctx;

	@Test
	public void testCatalogLookupPositive() {
		// TODO: pass applicationContext.xml to the constructor
		//ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		assertTrue("spring container should not be null", ctx != null);
		// TODO:
		/*
		 * Look up the musicCatalog, 
		 * assert that it's not null
	     * and invoke its toString method.  
		 * Don't forget to close spring context
		 */
		Catalog catalog=ctx.getBean(Catalog.class);

		assertNotNull(catalog);

		System.out.println("Size:"+catalog.size());

		assertTrue(catalog.size()>0);
		//ctx.close();
	}

	@Test
	public void testCatalogProfiles() {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		//ctx.getEnvironment().setActiveProfiles("dev");
		ctx.register(SpringConfig.class);
		ctx.refresh();

		Catalog catalog=ctx.getBean(Catalog.class);
		assertTrue(catalog.size()>0);

	}


	@Test
	public void testBeansPrototype(){
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		Catalog cat1=ctx.getBean(Catalog.class);
		Catalog cat2=ctx.getBean(Catalog.class);

		ItemRepository it1=ctx.getBean(ItemRepository.class);
		ItemRepository it2=ctx.getBean(ItemRepository.class);

		assertNotEquals(cat1,cat2);
		assertEquals(it1,it2);
	}


	// TODO: Not until Dependency Injection Lab - annotate as test method
	public void testCatalogPositive() {
		// TODO: Create the context, lookup the catalog.
		// TODO: DI Lab - call the size method test that it's greater than zero, and output its value
		// TODO: DI Lab - call the findByKeyword method with "a", test that there is at least 
		// one item found, and output the found items
		// TODO: Close the context.
	}

}
