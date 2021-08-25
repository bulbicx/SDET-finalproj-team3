package com.qa.choonz.unittest.dto;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private GenreDTO genreDTO = new GenreDTO(0L, "genre name", "genre desc", new ArrayList<>());
	private ArtistDTO artistDTO = new ArtistDTO(0L, "artist name", new ArrayList<>());
	private UserDTO userDTO = new UserDTO(0L, "username", "real name", new ArrayList<>());
	private AlbumDTO albumDTO = new AlbumDTO(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private PlaylistDTO playlistDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private TrackDTO trackDTO = new TrackDTO(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	
	//the only non standard elements in the domain are the ToStrings so we only need to test those
	
	
	@Test
	public void GenreTest() {
		assertThat("GenreDTO [id=0, name=genre name, description=genre desc, albums=[]]").isEqualTo(genreDTO.toString());
		
	}
	
	@Test
	public void ArtistTest() {
		assertThat("ArtistDTO [id=0, name=artist name, albums=[]]").isEqualTo(artistDTO.toString());
		
	}
	
	@Test
	public void UserTest() {
		assertThat("UserDTO [id=0, username=username, name=real name, playlists=[]]").isEqualTo(userDTO.toString());
		
	}
	
	@Test
	public void AlbumTest() {
		assertThat("AlbumDTO [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover=cover]").isEqualTo(albumDTO.toString());
		
	}
	
	@Test
	public void PlaylistTest() {
		assertThat("PlaylistDTO [id=0, name=playlist name, description=playlist desc, artwork=artwork, tracks=[], user="+user.toString()+"]").isEqualTo(playlistDTO.toString());
		
	}
	
	@Test
	public void TrackTest() {
		assertThat("TrackDTO [id=0, name=track name, album="+album.toString()+", playlist=[], duration=120, lyrics=lyrics]").isEqualTo(trackDTO.toString());
		
	}

}
