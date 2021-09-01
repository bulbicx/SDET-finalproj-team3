package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Artist;

public class ArtistDomainTest {
	
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	
	@Test
	public void ArtistTest() {
		assertThat("Artist [id=0, name=artist name, albums=[]]").isEqualTo(artist.toString());
		
	}

}
