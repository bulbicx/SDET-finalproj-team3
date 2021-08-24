package com.qa.choonz.unittest.domain;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;

@SpringBootTest
public class DomainTests {
	
	private Genre genre = new Genre(0, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0, "artist name", new ArrayList<>());
	private User user = new User(0, "username", "real name", "password", new ArrayList<>());
	private Album album = new Album(0, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Playlist playlist = new Playlist(0, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private Track track = new Track(0, "track name", album, playlist, 120, "lyrics");
	
	//the only non standard elements in the domain are the ToStrings so we only need to test those
	
	
	@Test
	public void GenreTest() {
		assertEquals("Genre [id=0, name=genre name, description=genre desc, albums=[]]",genre.toString());
		
	}
	
	@Test
	public void ArtistTest() {
		assertEquals("Artist [id=0, name=artist name, albums=[]]",artist.toString());
		
	}
	
	@Test
	public void UserTest() {
		assertEquals("User [id=0, username=username, name=real name, playlists=[]]",user.toString());
		
	}
	
	@Test
	public void AlbumTest() {
		assertEquals("Album [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]",album.toString());
		
	}
	
	@Test
	public void PlaylistTest() {
		assertEquals("Playlist [id=0, name=playlist name, description=playlist desc, artwork=artwork, tracks=[], user="+user.toString()+"]",playlist.toString());
		
	}
	
	@Test
	public void TrackTest() {
		assertEquals("Track [id=0, name=track name, album="+album.toString()+", playlist="+playlist.toString()+", duration=120, lyrics=lyrics]",track.toString());
		
	}


}
