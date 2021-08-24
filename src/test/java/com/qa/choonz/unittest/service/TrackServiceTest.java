package com.qa.choonz.unittest.service;

import static org.junit.Assert.assertEquals;

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
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;
import com.qa.choonz.service.TrackService;

@SpringBootTest
public class TrackServiceTest {

	@MockBean
	private TrackRepository repo;
	
	
	@Autowired
	private TrackService service;
	
	
	private Genre genre = new Genre(0, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private User user = new User(0, "username", "real name", "password", new ArrayList<>());
	private Album album = new Album(0, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Playlist playlist = new Playlist(0, "playlist name", "playlist desc", "artwork", new ArrayList<>(), user);
	private Track track = new Track(0, "track name", album, new ArrayList<>(), 120, "lyrics");
	private TrackDTO trackDTO = new TrackDTO(0, "track name", album, playlist, 120, "lyrics");
	private Optional<Track> optionalTrack = Optional.of(new Track(0, "track name", album, new ArrayList<>(), 120, "lyrics"));
	private Track newTrack = new Track(0, "new track name", album, new ArrayList<>(), 120, "new lyrics");
	private TrackDTO newTrackDTO = new TrackDTO(0, "new track name", album, playlist, 120, "new lyrics");
	
	
	//fails due to unupdated DTO object
	@Test
	public void TrackCreateTest() {
		
		Mockito.when(this.repo.save(track)).thenReturn(track);
		
		assertEquals(trackDTO,this.service.create(track));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(track);
	}
	
	@Test
	public void TrackReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertEquals(new ArrayList<>(), this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void TrackReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalTrack);

		assertEquals(trackDTO, this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void TrackUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalTrack);
		Mockito.when(this.repo.save(newTrack)).thenReturn(newTrack);
		
		assertEquals(newTrackDTO, this.service.update(newTrack, 0));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newTrack);
	}
	
	@Test
	public void TrackDeleteTest() {
		assertEquals(true,this.service.delete(0L));
	}
}
