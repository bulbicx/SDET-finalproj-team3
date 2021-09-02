package com.qa.choonz.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;


@SpringBootTest
public class PlaylistServiceTest {
	
	@MockBean
	private PlaylistRepository repo;
	
	@MockBean
	private UserRepository userRepo;
	
	@MockBean
	private TrackRepository trackRepo;
	
	@Autowired
	private PlaylistService service;
	
	private Image image = new Image(0L, "image name", "image type", null);
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	private Optional<User> optionalUser = Optional.of(new User(0L, "username", "real name", "password"));
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
	private PlaylistDTO playlistDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
	private Optional<Playlist> optionalPlaylist = Optional.of(new Playlist(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user));
	private Playlist newPlaylist = new Playlist(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
	private PlaylistDTO newPlaylistDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", image, new ArrayList<>(), user);
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>(), image);
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>(), image);
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, image);
	private Track track = new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	private Optional<Track> optionalTrack = Optional.of(new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics"));
	
	List<Track> tracklist = Stream.of(track).collect(Collectors.toList());
	private Playlist playlistWithTrack = new Playlist(0L, "playlist name", "playlist desc", image, tracklist, user);
	private Optional<Playlist> optionalPlaylistWithTrack = Optional.of(new Playlist(0L, "playlist name", "playlist desc", image, tracklist, user));
	private PlaylistDTO playlistWithTrackDTO = new PlaylistDTO(0L, "playlist name", "playlist desc", image, tracklist, user);

	
//	@Test
//	public void PlaylistCreateTest() {
//		
//		Mockito.when(this.userRepo.findById(0L)).thenReturn(optionalUser);
//		Mockito.when(this.repo.save(playlist)).thenReturn(playlist);
//		
//		assertThat(playlistDTO).isEqualTo(this.service.create(playlist, 0L));
//		
//		Mockito.verify(this.repo, Mockito.times(1)).save(playlist);
//		Mockito.verify(this.userRepo, Mockito.times(1)).findById(0L);
//	}
	
	@Test
	public void PlaylistReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertThat(new ArrayList<>()).isEqualTo(this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void PlaylistReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalPlaylist);
		
		assertThat(playlistDTO).isEqualTo(this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void PlaylistUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalPlaylist);
		Mockito.when(this.repo.save(newPlaylist)).thenReturn(newPlaylist);
		
		assertThat(newPlaylistDTO).isEqualTo(this.service.update(newPlaylist, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newPlaylist);
	}
	
	@Test
	public void PlaylistDeleteTest() {
		assertThat(true).isEqualTo(this.service.delete(0L));
	}
	
	@Test
	public void PlaylistAddTrackTest() {
		
		Mockito.when(this.trackRepo.findById(0L)).thenReturn(optionalTrack);
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalPlaylist);
		Mockito.when(this.repo.save(playlistWithTrack)).thenReturn(playlistWithTrack);
		
		assertThat(playlistWithTrackDTO).isEqualTo(this.service.addTrack(0L, 0L));
		
		Mockito.verify(this.trackRepo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(playlistWithTrack);
	}
	
	@Test
	public void PlaylistRemoveTrackTest() {
		Mockito.when(this.trackRepo.findById(0L)).thenReturn(optionalTrack);
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalPlaylistWithTrack);
		Mockito.when(this.repo.save(playlist)).thenReturn(playlist);
		
		assertThat(playlistDTO).isEqualTo(this.service.removeTrack(0L, 0L));
		
		Mockito.verify(this.trackRepo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(playlist);
		
	}

}
