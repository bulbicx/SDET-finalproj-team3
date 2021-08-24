//package com.qa.choonz.unittest.service;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.ArrayList;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.qa.choonz.persistence.domain.Artist;
//import com.qa.choonz.persistence.repository.ArtistRepository;
//import com.qa.choonz.rest.dto.ArtistDTO;
//import com.qa.choonz.service.ArtistService;
//
//@SpringBootTest
//public class ArtistServiceTest {
//	
//	@MockBean
//	private ArtistRepository repo;
//	
//	@Autowired
//	private ArtistService service;
//	
//	private Artist artist = new Artist(0L, "artist name", new ArrayList<>());
//	private ArtistDTO artistDTO = new ArtistDTO(0, "artist name", new ArrayList<>());
//	private Optional<Artist> optionalArtist = Optional.of(new Artist(0L, "artist name", new ArrayList<>()));
//	private Artist newArtist = new Artist(0L, "new artist name", new ArrayList<>());
//	private ArtistDTO newArtistDTO = new ArtistDTO(0, "new artist name", new ArrayList<>());
//	
//	@Test
//	public void ArtistCreateTest() {
//		
//		Mockito.when(this.repo.save(artist)).thenReturn(artist);
//		
//		assertEquals(artistDTO,this.service.create(artist));
//		
//		Mockito.verify(this.repo, Mockito.times(1)).save(artist);
//	}
//	
//	@Test
//	public void ArtistReadAllTest() {
//		
//		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
//		
//		assertEquals(new ArrayList<>(), this.service.read());
//		
//		Mockito.verify(this.repo, Mockito.times(1)).findAll();
//	}
//	
//	@Test
//	public void ArtistReadByIdTest() {
//		Mockito.when(this.repo.findById(0L)).thenReturn(optionalArtist);
//		
//		assertEquals(artistDTO, this.service.read(0L));
//		
//		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
//	}
//	
//	@Test
//	public void ArtistUpdateTest() {
//		Mockito.when(this.repo.findById(0L)).thenReturn(optionalArtist);
//		Mockito.when(this.repo.save(newArtist)).thenReturn(newArtist);
//		
//		assertEquals(newArtistDTO, this.service.update(newArtist, 0));
//		
//		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
//		Mockito.verify(this.repo, Mockito.times(1)).save(newArtist);
//	}
//	
//	@Test
//	public void ArtistDeleteTest() {
//		assertEquals(true,this.service.delete(0L));
//	}
//
//}
