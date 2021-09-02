package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;

public class AlbumDomainTest {
	
	
	private Image image = new Image(0L, "image name", "image type", null);
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>(), image);
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>(), image);
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, image);

	
	@Test
	public void AlbumTest() {
		assertThat("Album [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover="+image.toString()+"]").isEqualTo(album.toString());
		
	}
	

}
