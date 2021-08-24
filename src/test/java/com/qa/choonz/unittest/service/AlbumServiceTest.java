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
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@SpringBootTest
public class AlbumServiceTest {

	@MockBean
	private AlbumRepository repo;
	
	@Autowired
	private AlbumService service;
	
	private Genre genre = new Genre(0, "genre name", "genre desc", new ArrayList<>());
	private Artist artist = new Artist(0, "artist name", new ArrayList<>());
	private Album album = new Album(0, "album name",  new ArrayList<>(), artist, genre, "cover");
	private AlbumDTO albumDTO = new AlbumDTO(0, "album name",  new ArrayList<>(), artist, genre, "cover");
	private Optional<Album> optionalAlbum = Optional.of(new Album(0, "album name",  new ArrayList<>(), artist, genre, "cover"));
	private Album newAlbum = new Album(0, "new album name",  new ArrayList<>(), artist, genre, "new cover");
	private AlbumDTO newAlbumDTO = new AlbumDTO(0, "new album name",  new ArrayList<>(), artist, genre, "new cover");
	
	@Test
	public void AlbumCreateTest() {
		
		Mockito.when(this.repo.save(album)).thenReturn(album);
		
		assertEquals(albumDTO,this.service.create(album));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(album);
	}
	
	@Test
	public void AlbumReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertEquals(new ArrayList<>(), this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void AlbumReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalAlbum);
		
		assertEquals(albumDTO, this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void AlbumUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalAlbum);
		Mockito.when(this.repo.save(newAlbum)).thenReturn(newAlbum);
		
		assertEquals(newAlbumDTO, this.service.update(newAlbum, 0));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newAlbum);
	}
	
	@Test
	public void AlbumDeleteTest() {
		assertEquals(true,this.service.delete(0L));
	}
}
