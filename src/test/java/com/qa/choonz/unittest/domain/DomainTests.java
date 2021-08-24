package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private Track track = new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	
	//the only non standard elements in the domain are the ToStrings so we only need to test those
	
	
	@Test
	public void GenreTest() {
		assertThat("Genre [id=0, name=genre name, description=genre desc, albums=[]]").isEqualTo(genre.toString());
		
	}
	
	@Test
	public void ArtistTest() {
		assertThat("Artist [id=0, name=artist name, albums=[]]").isEqualTo(artist.toString());
		
	}
	
	@Test
	public void UserTest() {
		assertThat("User [id=0, username=username, name=real name, playlists=[]]").isEqualTo(user.toString());
		
	}
	
	@Test
	public void AlbumTest() {
		assertThat("Album [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]").isEqualTo(album.toString());
		
	}
	
	@Test
	public void PlaylistTest() {
		assertThat("Playlist [id=0, name=playlist name, description=playlist desc, artwork=artwork, tracks=[], user="+user.toString()+"]").isEqualTo(playlist.toString());
		
	}
	
	@Test
	public void TrackTest() {
		assertThat("Track [id=0, name=track name, album="+album.toString()+", playlist=[], duration=120, lyrics=lyrics]").isEqualTo(track.toString());
		
	}


}
