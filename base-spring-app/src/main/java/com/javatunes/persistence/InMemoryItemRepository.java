/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.persistence;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javatunes.domain.MusicCategory;
import com.javatunes.domain.MusicItem;

public class InMemoryItemRepository implements ItemRepository {

	/** This is simply to eliminate the need to provide an in-memory database! */
	private List<MusicItem> catalogData =  new ArrayList<>(Arrays.asList(
			new MusicItem(1L, "Diva", "Annie Lennox", "1992-01-04", new BigDecimal("13.99"), MusicCategory.POP),
			new MusicItem(2L,"Dream of the Blue Turtles", "Sting", "1985-02-05", new BigDecimal("14.99"), MusicCategory.POP),
			new MusicItem(3L,"Trouble is...", "Kenny Wayne Shepherd Band", "1997-08-08", new BigDecimal("14.99"), MusicCategory.BLUES),
			new MusicItem(4L,"Lie to Me", "Jonny Lang", "1997-08-26", new BigDecimal("17.97"), MusicCategory.BLUES),
			new MusicItem(5L,"Little Earthquakes", "Tori Amos", "1992-01-18", new BigDecimal("14.99"), MusicCategory.ALTERNATIVE),
			new MusicItem(6L,"Seal", "Seal", "1991-08-18", new BigDecimal("17.97"), MusicCategory.POP),
			new MusicItem(7L,"Ian Moore", "Ian Moore", "1993-12-05", new BigDecimal("9.97"), MusicCategory.CLASSICAL),
			new MusicItem(8L,"So Much for the Afterglow", "Everclear", "1997-01-19", new BigDecimal("13.99"), MusicCategory.ROCK),
			new MusicItem(9L,"Surfacing", "Sarah McLachlan", "1997-12-04", new BigDecimal("17.97"), MusicCategory.ALTERNATIVE),
			new MusicItem(10L,"Hysteria", "Def Leppard", "1987-06-20", new BigDecimal("17.97"), MusicCategory.ROCK),
			new MusicItem(11L,"A Life of Saturdays", "Dexter Freebish", "2000-12-06", new BigDecimal("16.97"), MusicCategory.RAP),
			new MusicItem(12L,"Human Clay", "Creed", "1999-10-21", new BigDecimal("18.97"), MusicCategory.ROCK),
			new MusicItem(13L,"My, I'm Large", "Bobs", "1987-02-20", new BigDecimal("11.97"), MusicCategory.COUNTRY),
			new MusicItem(14L,"So", "Peter Gabriel", "1986-10-03", new BigDecimal("17.97"), MusicCategory.POP),
			new MusicItem(15L,"Big Ones", "Aerosmith", "1994-05-08", new BigDecimal("18.97"),MusicCategory.ROCK),
			new MusicItem(16L,"90125", "Yes", "1983-10-16", new BigDecimal("11.97"),MusicCategory.ROCK),
			new MusicItem(17L,"1984", "Van Halen", "1984-08-19", new BigDecimal("11.97"), MusicCategory.ROCK),
			new MusicItem(18L,"Escape", "Journey", "1981-02-25", new BigDecimal("11.97"), MusicCategory.CLASSIC_ROCK)
		));

	private Integer maxSearchResults = 30;

	// Accessors
	public Integer getMaxSearchResults() {
		return maxSearchResults;
	}
	public void setMaxSearchResults(Integer maxSearchResults) {
		this.maxSearchResults = maxSearchResults;
	}

	@Override
	public MusicItem findOne(Long id) {
		// Uses Java 8 Streams, and filters with a lambda that matches on id value.  
	    Optional<MusicItem> itemOptional = catalogData.stream()
	            .filter(item -> item.getId().equals(id))
	            .findAny();		
	    return itemOptional.orElse(null);  // Returns item if found, else returns null
	}
		
	@Override
	public Collection<MusicItem> findByArtistContainingOrTitleContainingAllIgnoreCase(String artist, String title) {

		String artistLow = artist.toLowerCase();
		String titleLow = title.toLowerCase();

		// Uses Java 8 Streams, and filters with a lambda that matches on title or artist
		// Uses a "finisher" to limit the side of the list.  This can be done more efficiently by limiting while you're creating the list
		// We do it this way for (relative) simplicity.  See https://stackoverflow.com/questions/33853611/limit-groupby-in-java-8 for guide to a more efficient solution
	    return catalogData.stream()
	            .filter(item -> item.getTitle().toLowerCase().contains(titleLow) ||
	                            item.getArtist().toLowerCase().contains(artistLow))
	            .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.size() <= maxSearchResults ? list : list.subList(0, maxSearchResults)));		
	}
	
	@Override
	public Collection<MusicItem> findByMusicCategory(MusicCategory category) {
		// Uses Java 8 Streams, and filters with a lambda that matches on musicCategory
		// Uses a "finisher" to limit the side of the list, same as findByArtistContainingOrTitleContainingAllIgnoreCase
	    return catalogData.stream()
	            .filter(item->item.getMusicCategory() == category)
	            .collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.size() <= maxSearchResults ? list : list.subList(0, maxSearchResults)));	            
	}

	public Collection<MusicItem> findAll() {
		return Collections.unmodifiableCollection(catalogData);
	}

	@Override
	public long count() {
		return catalogData.size();
	}

	@Override
	public MusicItem save(MusicItem item) {
		// Not implemented
		return null;
	}

	@Override
	public void delete(MusicItem item) {
		// Not implemented
	}

}
