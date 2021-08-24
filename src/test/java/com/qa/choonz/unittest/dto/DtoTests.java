package com.qa.choonz.unittest.dto;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.rest.dto.UserDTO;

@SpringBootTest
public class DtoTests {
	
	private Genre genre = new Genre(0, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private User user = new User(0, "username", "real name", "password", new ArrayList<>());
	private Album album = new Album(0, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Playlist playlist = new Playlist(0, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private GenreDTO genreDTO = new GenreDTO(0, "genre name", "genre desc", new ArrayList<>());
	private ArtistDTO artistDTO = new ArtistDTO(0, "artist name", new ArrayList<>());
	private UserDTO userDTO = new UserDTO(0, "username", "real name", new ArrayList<>());
	private AlbumDTO albumDTO = new AlbumDTO(0, "album name",  new ArrayList<>(), artist, genre, "cover");
	private PlaylistDTO playlistDTO = new PlaylistDTO(0, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private TrackDTO trackDTO = new TrackDTO(0, "track name", album, playlist, 120, "lyrics");
	
	//the only non standard elements in the domain are the ToStrings so we only need to test those
	
	
	@Test
	public void GenreTest() {
		assertEquals("GenreDTO [id=0, name=genre name, description=genre desc, albums=[]]",genreDTO.toString());
		
	}
	
	@Test
	public void ArtistTest() {
		assertEquals("ArtistDTO [id=0, name=artist name, albums=[]]",artistDTO.toString());
		
	}
	
	@Test
	public void UserTest() {
		assertEquals("UserDTO [id=0, username=username, name=real name, playlists=[]]",userDTO.toString());
		
	}
	
	@Test
	public void AlbumTest() {
		assertEquals("AlbumDTO [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]",albumDTO.toString());
		
	}
	
	@Test
	public void PlaylistTest() {
		assertEquals("PlaylistDTO [id=0, name=playlist name, description=playlist desc, artwork=artwork, tracks=[], user="+user.toString()+"]",playlistDTO.toString());
		
	}
	
	@Test
	public void TrackTest() {
		assertEquals("TrackDTO [id=0, name=track name, album="+album.toString()+", playlist="+playlist.toString()+", duration=120, lyrics=lyrics]",trackDTO.toString());
		
	}

}
