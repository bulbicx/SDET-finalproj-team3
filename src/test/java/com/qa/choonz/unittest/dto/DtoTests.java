package com.qa.choonz.unittest.dto;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.rest.dto.PublicUserDTO;

@SpringBootTest
public class DtoTests {
	
	private Image image = new Image(0L, "image name", "image type", null);
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>(), image);
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>(), image);
	private PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>(), new ArrayList<>());
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, image);
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
	private GenreDTO genreDTO = new GenreDTO(0L, "genre name", "genre desc", new ArrayList<>());
	private ArtistDTO artistDTO = new ArtistDTO(0L, "artist name", new ArrayList<>(), image);
	private PublicUserDTO userDTO = new PublicUserDTO(0L, "username", "real name", new ArrayList<>());
	private AlbumDTO albumDTO = new AlbumDTO(0L, "album name",  new ArrayList<>(), artist, genre, image);
	private PlaylistDTO playlistDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
	private TrackDTO trackDTO = new TrackDTO(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	
	//the only non standard elements in the domain are the ToStrings so we only need to test those
	
	
	@Test
	public void GenreTest() {
		assertThat("GenreDTO [id=0, name=genre name, description=genre desc, albums=[], image=null]").isEqualTo(genreDTO.toString());
		
	}
	
	@Test
	public void ArtistTest() {
		assertThat("ArtistDTO [id=0, name=artist name, albums=[], image="+image.toString()+"]").isEqualTo(artistDTO.toString());
		
	}
	
	@Test
	public void UserTest() {
		assertThat("UserDTO [id=0, username=username, name=real name, playlists=[]]").isEqualTo(userDTO.toString());
		
	}
	
	@Test
	public void AlbumTest() {
		assertThat("AlbumDTO [id=0, name=album name, tracks=[], artist="+artist.toString()+", genre="+genre.toString()+", cover="+image.toString()+"]").isEqualTo(albumDTO.toString());
		
	}
	
	@Test
	public void PlaylistTest() {
		assertThat("PlaylistDTO [id=0, name=playlist name, description=playlist desc, artwork="+image.toString()+", tracks=[], user="+user.toString()+"]").isEqualTo(playlistDTO.toString());
		
	}
	
	@Test
	public void TrackTest() {
		assertThat("TrackDTO [id=0, name=track name, album="+album.toString()+", playlist=[], duration=120, lyrics=lyrics]").isEqualTo(trackDTO.toString());
		
	}

}
