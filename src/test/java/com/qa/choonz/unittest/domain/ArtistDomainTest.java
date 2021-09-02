package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Image;

public class ArtistDomainTest {
	
	private Image image = new Image(0L, "image name", "image type", null);
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>(), image);
	
	@Test
	public void ArtistTest() {
		assertThat("Artist [id=0, name=artist name, albums=[], cover="+image.toString()+"]").isEqualTo(artist.toString());
		
	}

}
