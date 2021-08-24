package com.qa.choonz.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;

@SpringBootTest
public class TrackServiceTest {

	@MockBean
	private TrackRepository repo;
	
	@MockBean
	private AlbumRepository albumRepo;
	
	
	@Autowired
	private TrackService service;
	
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Optional<Album> optionalAlbum = Optional.of(new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover"));
	private Playlist playlist = new Playlist(0L, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private Track track = new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	private TrackDTO trackDTO = new TrackDTO(0L, "track name", album, playlist, 120, "lyrics");
	private Optional<Track> optionalTrack = Optional.of(new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics"));
	private Track newTrack = new Track(0L, "new track name", album, new ArrayList<>(), 120, "new lyrics");
	private TrackDTO newTrackDTO = new TrackDTO(0L, "new track name", album, playlist, 120, "new lyrics");
	
	//there's inconsistency between usage of save and saveAndFlush. If it's supposed to be that way, cool but make sure you know which it's supposed to be
	@Test
	public void TrackCreateTest() {
		
		Mockito.when(this.albumRepo.findById(0L)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.saveAndFlush(track)).thenReturn(track);
		
		assertThat(trackDTO).isEqualTo(this.service.create(track,0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).saveAndFlush(track);
		Mockito.verify(this.albumRepo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void TrackReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertThat(new ArrayList<>()).isEqualTo(this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void TrackReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalTrack);

		assertThat(trackDTO).isEqualTo(this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void TrackUpdateTest() {
		
		Mockito.when(this.albumRepo.findById(0L)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalTrack);
		Mockito.when(this.repo.save(newTrack)).thenReturn(newTrack);
		
		assertThat(newTrackDTO).isEqualTo(this.service.update(newTrack, 0L, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newTrack);
		Mockito.verify(this.albumRepo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void TrackDeleteTest() {
		assertThat(true).isEqualTo(this.service.delete(0L));
	}
}
