package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Genre;

public class GenreDomainTest {
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	
	@Test
	public void GenreTest() {
		assertThat("Genre [id=0, name=genre name, description=genre desc, albums=[]]").isEqualTo(genre.toString());
		
	}
	

}
