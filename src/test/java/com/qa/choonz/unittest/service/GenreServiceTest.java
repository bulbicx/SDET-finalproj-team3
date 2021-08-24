package com.qa.choonz.unittest.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;

@SpringBootTest
public class GenreServiceTest {
	
	@MockBean
	private GenreRepository repo;
	
	@Autowired
	private GenreService service;
	
	private Genre genre = new Genre(0, "genre name", "genre desc", new ArrayList<>());
	private GenreDTO genreDTO = new GenreDTO(0, "genre name", "genre desc", new ArrayList<>());
	private Optional<Genre> optionalGenre = Optional.of(new Genre(0, "genre name", "genre desc", new ArrayList<>()));
	private Genre newGenre = new Genre(0, "new genre name", "new genre desc", new ArrayList<>());
	private GenreDTO newGenreDTO = new GenreDTO(0, "new genre name", "new genre desc", new ArrayList<>());
	
	
	@Test
	public void GenreCreateTest() {
		
		Mockito.when(this.repo.save(genre)).thenReturn(genre);
		
		assertEquals(genreDTO,this.service.create(genre));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(genre);
	}
	
	@Test
	public void GenreReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertEquals(new ArrayList<>(), this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void GenreReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalGenre);
		
		assertEquals(genreDTO, this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void GenreUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalGenre);
		Mockito.when(this.repo.save(newGenre)).thenReturn(newGenre);
		
		assertEquals(newGenreDTO, this.service.update(newGenre, 0));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newGenre);
	}
	
	@Test
	public void GenreDeleteTest() {
		assertEquals(true,this.service.delete(0L));
	}

}
