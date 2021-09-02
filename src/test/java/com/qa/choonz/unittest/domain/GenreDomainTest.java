package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;

public class GenreDomainTest {
	
	private Image image = new Image(0L, "image name", "image type", null);
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>(), image);
	
	@Test
	public void GenreTest() {
		assertThat("Genre [id=0, name=genre name, description=genre desc, albums=[], image="+image.toString()+"]").isEqualTo(genre.toString());
		
	}
	

}
