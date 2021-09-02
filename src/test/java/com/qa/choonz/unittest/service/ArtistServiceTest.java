package com.qa.choonz.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.service.ArtistService;

@SpringBootTest
public class ArtistServiceTest {
	
	@MockBean
	private ArtistRepository repo;
	
	@Autowired
	private ArtistService service;
	
	private Image image = new Image(0L, "image name", "image type", null);
	private Artist artist = new Artist(0L, "artist name", new ArrayList<>(), image);
	private ArtistDTO artistDTO = new ArtistDTO(0L, "artist name", new ArrayList<>(), null);
	private Optional<Artist> optionalArtist = Optional.of(new Artist(0L, "artist name", new ArrayList<>(), image));
	private Artist newArtist = new Artist(0L, "new artist name", new ArrayList<>(), image);
	private ArtistDTO newArtistDTO = new ArtistDTO(0L, "new artist name", new ArrayList<>(), null);
	
//	@Test
//	public void ArtistCreateTest() {
//		
//		Mockito.when(this.repo.save(artist)).thenReturn(artist);
//		
//		assertThat(artistDTO).isEqualTo(this.service.create(artist));
//		
//		Mockito.verify(this.repo, Mockito.times(1)).save(artist);
//	}
	
	@Test
	public void ArtistReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertThat(new ArrayList<>()).isEqualTo(this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void ArtistReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalArtist);
		
		assertThat(artistDTO).isEqualTo(this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void ArtistUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalArtist);
		Mockito.when(this.repo.save(newArtist)).thenReturn(newArtist);
		
		assertThat(newArtistDTO).isEqualTo(this.service.update(newArtist, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newArtist);
	}
	
	@Test
	public void ArtistDeleteTest() {
		assertThat(true).isEqualTo(this.service.delete(0L));
	}

}
