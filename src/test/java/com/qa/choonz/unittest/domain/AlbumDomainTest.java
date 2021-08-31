package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

public class AlbumDomainTest {
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover");

	
	@Test
	public void AlbumTest() {
		assertThat("Album [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]").isEqualTo(album.toString());
		
	}
	
	@Test
	public void AlbumTest2() {
		Album altAlbum = new Album("album name",  new ArrayList<>(), artist, genre, "cover");
		assertThat("Album [id=null, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]").isEqualTo(altAlbum.toString());
		
	}
	
	@Test
	public void AlbumTest3() {
		Album altAlbum = new Album(0L, "album name", artist, genre, "cover");
		assertThat("Album [id=0, name=album name, tracks=null, artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]").isEqualTo(altAlbum.toString());
		
	}
	
	@Test
	public void AlbumTest4() {
		Album altAlbum = new Album("album name", artist, genre, "cover");
		assertThat("Album [id=null, name=album name, tracks=null, artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]").isEqualTo(altAlbum.toString());
		
	}
	
	@Test
	public void AlbumTest5() {
		Album altAlbum = new Album(0L, "album name", "cover");
		assertThat("Album [id=0, name=album name, tracks=null, artist=null, genre=null, cover=cover]").isEqualTo(altAlbum.toString());
		
	}
	
	@Test
	public void AlbumTest6() {
		Album altAlbum = new Album("album name", "cover");
		assertThat("Album [id=null, name=album name, tracks=null, artist=null, genre=null, cover=cover]").isEqualTo(altAlbum.toString());
		
	}

}
