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
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@SpringBootTest
public class AlbumServiceTest {

	@MockBean
	private AlbumRepository repo;
	
	@MockBean
	private ArtistRepository artistRepo;
	
	@MockBean 
	private GenreRepository genreRepo;
	
	@MockBean TrackRepository trackRepo;
	
	@Autowired
	private AlbumService service;
	
	private Genre genre = new Genre(0L, "genre name", "genre desc", new ArrayList<>());
	private Optional<Genre> optionalGenre = Optional.of(new Genre(0L, "genre name", "genre desc", new ArrayList<>()));
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
	private Optional<Artist> optionalArtist = Optional.of(new Artist(0L, "artist name", new ArrayList<>()));
	private Album album = new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private AlbumDTO albumDTO = new AlbumDTO(0L, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Optional<Album> optionalAlbum = Optional.of(new Album(0L, "album name",  new ArrayList<>(), artist, genre, "cover"));
	private Album newAlbum = new Album(0L, "new album name",  new ArrayList<>(), artist, genre, "new cover");
	private AlbumDTO newAlbumDTO = new AlbumDTO(0L, "new album name",  new ArrayList<>(), artist, genre, "new cover");
	private Track track = new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics");
	private Optional<Track> optionalTrack = Optional.of(new Track(0L, "track name", album, new ArrayList<>(), 120, "lyrics"));
	
	List<Track> tracklist = Stream.of(track).collect(Collectors.toList());
	private Album albumWithTrack = new Album(0L, "album name",  tracklist, artist, genre, "cover");
	private Optional<Album> optionalAlbumWithTrack = Optional.of(new Album(0L, "album name",  tracklist, artist, genre, "cover"));
	private AlbumDTO albumWithTrackDTO = new AlbumDTO(0L, "album name",  tracklist, artist, genre, "cover");
	
	
	@Test
	public void AlbumCreateTest() {
		
		Mockito.when(this.repo.save(album)).thenReturn(album);
		Mockito.when(this.artistRepo.findById(0L)).thenReturn(optionalArtist);
		Mockito.when(this.genreRepo.findById(0L)).thenReturn(optionalGenre);
		
		assertThat(albumDTO).isEqualTo(this.service.create(album, 0L, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(album);
		Mockito.verify(this.artistRepo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.genreRepo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void AlbumReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertThat(new ArrayList<>()).isEqualTo(this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void AlbumReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalAlbum);
		
		assertThat(albumDTO).isEqualTo(this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void AlbumUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.save(newAlbum)).thenReturn(newAlbum);
		Mockito.when(this.artistRepo.findById(0L)).thenReturn(optionalArtist);
		Mockito.when(this.genreRepo.findById(0L)).thenReturn(optionalGenre);
		
		assertThat(newAlbumDTO).isEqualTo(this.service.update(newAlbum, 0L, 0L, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newAlbum);
		Mockito.verify(this.artistRepo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.genreRepo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void AlbumDeleteTest() {
		assertThat(true).isEqualTo(this.service.delete(0L));
	}
	
	@Test
	public void AlbumAddTrackTest() {
		
		Mockito.when(this.trackRepo.findById(0L)).thenReturn(optionalTrack);
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.save(albumWithTrack)).thenReturn(albumWithTrack);
		
		assertThat(albumWithTrackDTO).isEqualTo(this.service.addTrack(0L, 0L));
		
		Mockito.verify(this.trackRepo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(albumWithTrack);
	}
	
	@Test
	public void AlbumRemoveTrackTest() {
		Mockito.when(this.trackRepo.findById(0L)).thenReturn(optionalTrack);
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalAlbumWithTrack);
		Mockito.when(this.repo.save(album)).thenReturn(album);
		
		assertThat(albumDTO).isEqualTo(this.service.removeTrack(0L, 0L));
		
		Mockito.verify(this.trackRepo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(album);
		
	}
}
