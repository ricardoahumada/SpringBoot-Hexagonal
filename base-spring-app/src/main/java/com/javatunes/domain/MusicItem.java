/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**
 * Holds details for music items.  Requires Java 8+ for LocalDate support.
 *
 */
public class MusicItem {

	private Long id;
	private String title, artist;
	private LocalDate releaseDate;
	private BigDecimal price;
	private MusicCategory musicCategory;
	
	// For internal use only.
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public MusicItem() {
	}

	/**
	 * Convenience constructor to initialize only the id
	 * @param id the id
	 */
	public MusicItem(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	/**
	 * Constructor not requiring an id - useful for creating new persistent entities
	 * @param title title of the item
	 * @param artist artist/band of the item
	 * @param releaseDate date it was released
	 * @param price price
	 * @param musicCategory The type of music
	 */
	public MusicItem(String title, String artist, String releaseDate,
			BigDecimal price, MusicCategory musicCategory) {
		this.setTitle(title);
		this.setArtist(artist);
		this.setReleaseDateAsString(releaseDate);
		this.setPrice(price);
		this.setMusicCategory(musicCategory);
	}
	
	/**
	 * Constructor requiring an id - useful to create instances for in-memory data stores
	 * @param id item's id
	 * @param title item's title
	 * @param artist artist/band of the item
	 * @param releaseDate date it was released
	 * @param price sell price
	 * @param musicCategory The type of music
	 */
	public MusicItem(Long id, String title, String artist, String releaseDate,
			BigDecimal price, MusicCategory musicCategory) {
		this.setId(id);
		this.setTitle(title);
		this.setArtist(artist);
		this.setReleaseDateAsString(releaseDate);
		this.setPrice(price);
		this.setMusicCategory(musicCategory);
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(LocalDate releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * For ease of setting a date via strings with a pattern like 2017-12-25 
	 * @param releaseDateString string with date pattern yyyy-MM-dd
	 */
	public void setReleaseDateAsString(String releaseDateString) {
		releaseDate = LocalDate.parse(releaseDateString, formatter); 
	}

	/**
	 * Value (business) equality.  Checks equality of the title, artist and releaseDate properties.
	 */
	@Override
	public boolean equals(Object o) {
		boolean result = false;
		if (o == this) {
			result = true;
		}
		else if (o instanceof MusicItem) {
			MusicItem other = (MusicItem) o;
			result = Objects.equals(this.getTitle(),       other.getTitle()) &&
					Objects.equals(this.getArtist(),      other.getArtist()) &&
					Objects.equals(this.getReleaseDate(), other.getReleaseDate());
		}
		return result; 
	}

	/**
	 * Hash code consistent with equals(). Uses the title, artist and releaseDate properties to compute hash.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.title, this.artist, this.releaseDate);
	} 
    
	public MusicCategory getMusicCategory() {
		return musicCategory;
	}

	public void setMusicCategory(MusicCategory musicCategory) {
		this.musicCategory = musicCategory;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MusicItem [id=" + id + ", title=" + title + ", artist="
				+ artist + ", releaseDate=" + releaseDate + ", price=" + price
				+ ", musicCategory=" + musicCategory + "]";
	}

}
