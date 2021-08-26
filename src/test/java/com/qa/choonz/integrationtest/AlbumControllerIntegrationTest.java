package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:sql-schema.sql", "classpath:sql-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreateAlbum() throws Exception {
		
		//Create Album object
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>());
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>());
		Album album = new Album("Blackpool", artist, genre, "image");
		
		//Convert it to a JSON String
		String albumAsJSON = this.mapper.writeValueAsString(album);
		
		//Build mock request
		RequestBuilder mockRequest =
								post("/albums/create/1/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(albumAsJSON);
		
		//Create an album object resembling the one created in database
		Album albumInDb = new Album(2L, "Blackpool", artist, genre, "image");
		
		
		//Convert the album in database as JSON
		String albumInDbAsJSON = this.mapper.writeValueAsString(albumInDb);
		
		//Get status created
		ResultMatcher matchStatus = status().isCreated();
		
		//Get body
		ResultMatcher matchBody = content().json(albumInDbAsJSON);
		
		//Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadAllAlbums() throws Exception {
		
		RequestBuilder mockRequest = get("/albums/read");
		
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>());
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>());
		Album album = new Album(1L, "Blackpool", artist, genre, "image");
		
		List<Album> albumsOnDb = new ArrayList<>();
		albumsOnDb.add(album);
		
		String albumsOnDbAsJSON = this.mapper.writeValueAsString(albumsOnDb);
		
		ResultMatcher matchStatus = status().isOk();
		
		ResultMatcher matchBody = content().json(albumsOnDbAsJSON);
		
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
		
	}
	
	@Test
	void testReadOneAlbum() throws Exception {
		
		RequestBuilder mockRequest = get("/albums/read/1");
		
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>());
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>());
		Album album = new Album(1L, "Blackpool", artist, genre, "image");
		
		
		String albumAsJSON = this.mapper.writeValueAsString(album);
		
		ResultMatcher matchStatus = status().isOk();
		
		ResultMatcher matchBody = content().json(albumAsJSON);
		
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
		
	}
	
	@Test
	void testUpdateAlbum() throws Exception {
		//Create artist with updated data
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>());
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>());
		Album updatedAlbum = new Album(1L, "Bluepool", artist, genre, "image2");
		
		//Convert artist into JSON format
		String updatedAlbumAsJSON = this.mapper.writeValueAsString(updatedAlbum);
		
		//Build mock request
		RequestBuilder mockRequest =
								put("/albums/update/1/1/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(updatedAlbumAsJSON);
		
		//Create artist object which resemble the updated one on db
		Album updatedAlbumOnDb = new Album(1L, "Bluepool", artist, genre, "image2");
		
		//Convert artist as JSON format
		String updatedAlbumOnDbAsJSON = this.mapper.writeValueAsString(updatedAlbumOnDb); 
		
		//Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();
		
		//Get content
		ResultMatcher matchBody = content().json(updatedAlbumOnDbAsJSON);
		
		//Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testDeleteAlbum() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = delete("/albums/delete/1");
		
		//Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();
		
		//Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}


}
