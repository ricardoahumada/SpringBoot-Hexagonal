/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.persistence;

import java.util.Collection;

import com.javatunes.domain.MusicCategory;
import com.javatunes.domain.MusicItem;

/**
 * Standard repository interface for accessing MusicItem instances.
 * Follows Spring Data naming conventions for repositories (even if Spring/Spring Data are not used).
 * @author Yaakov Weintraub
 *
 */
public interface ItemRepository {

	/**
	 * Find one item by id 
	 * @param id the id
	 * @return the item with matching id, or null if not found.
	 */
	public MusicItem findOne(Long id);
	/**
	 * Get all items in the repository.
	 */
	public Collection<MusicItem> findAll();
	
	/**
	 * Short, single parameter version of findByArtistContainingOrTitleContainingAllIgnoreCase
	 * @param keyword
	 * @return
	 */
	public default Collection<MusicItem> findByKeyword(String keyword) {
		return findByArtistContainingOrTitleContainingAllIgnoreCase(keyword, keyword);
	}
	/**
	 * Search on title and artist that contain the given keywords (i.e. are the keywords substrings)  
	 * @param artist artist to match on
	 * @param title title to match on
	 * @return The matching items - never null, but may be empty
	 */
	public Collection<MusicItem> findByArtistContainingOrTitleContainingAllIgnoreCase(String artist, String title);
	/**
	 * Search on music category
	 * @param category The category you want
	 * @return The matching items - never null, but may be empty
	 */
	public Collection<MusicItem> findByMusicCategory(MusicCategory category);
	
	/**
	 * @return Total number of items in the repository
	 */
	public long count();

	/**
	 * Save a new item
	 * @param item item to save
	 * @return
	 */
	public MusicItem save(MusicItem item);
	/**
	 * Delete the passed in item.  Generally deletes the item with the passed in id.
	 * @param item item to delete
	 */
	public void delete(MusicItem item);
}
