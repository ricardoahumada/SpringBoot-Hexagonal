/*
 * This code is sample code, provided as-is, and we make NO 
 * warranties as to its correctness or suitability for any purpose.
 * 
 * We hope that it's useful to you. Enjoy. 
 * Copyright LearningPatterns Inc.
 */

package com.javatunes.domain;

/**
 * Defines available categories of music.  
 * Declares a constructor requiring a (human friendly) description, so values in the eunm declaration 
 * are of the form ENUM_NAME ("description string")
 *
 */
public enum MusicCategory {
	ALTERNATIVE ("Alternative"), 
	BLUES ("Blues"), 
	CLASSICAL ("Classical"), 
	CLASSIC_ROCK ("Classic Rock"), 
	COUNTRY ("Country"), 
	JAZZ ("Jazz"), 
	POP ("Pop"),
	RAP ("Rap"),
	ROCK ("Rock");

	/** A human-friendly string value */
	private String description;

	private MusicCategory (String description) {
		this.description = description;
	}

	/**
	 * Public accessor for the human friendly description.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
}
