/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */
 
package com.javatunes.service;

import java.util.Collection;

import com.javatunes.domain.MusicItem;
import com.javatunes.persistence.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.inject.Named;

// TODO: implement appropriate interface
//@Named("CatalogMusic")
public class CatalogImpl implements Catalog{

	//@Inject
	private ItemRepository itemRepository;

	public CatalogImpl(){
	}

	//@Inject
	public CatalogImpl(ItemRepository itemRepository){
		this.itemRepository=itemRepository;
	}

	public void setItemRepository(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}
	public MusicItem findById(Long id) {
		return itemRepository.findOne(id);
	}

	@Override
	public Collection<MusicItem> findByKeyword(String keyword) {
		return itemRepository.findByKeyword(keyword);
	}

	@Override
	public long size() {
		return itemRepository.count();
	}

	@Override
	public String toString() {
		return "I am a shiny new " + getClass().getName() + " brought to you from Spring" + " but you can just call me " + getClass().getInterfaces()[0] + ".  My itemRepository is " + itemRepository;
	}

}
