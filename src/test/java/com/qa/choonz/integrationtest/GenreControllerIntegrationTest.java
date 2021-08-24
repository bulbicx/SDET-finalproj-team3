package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:sql-schema.sql", "classpath:sql-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class GenreControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;//Convert to JSON
	
	@Test
	void testCreateArtist() throws Exception {
		//Create Genre object
		Genre genre = new Genre("hip hop", "hip hop genre");
		
		//Convert it to a JSON String
		String genreAsJSON = this.mapper.writeValueAsString(genre);
		
		//Build mock request
		RequestBuilder mockRequest =
								post("/genres/create")
								.contentType(MediaType.APPLICATION_JSON)
								.content(genreAsJSON);
		
		//Create an Genre object resembling the one created in database
		Genre genreInDb = new Genre(2L, "hip hop", "hip hop genre");
		
		//Convert the genre resembling the one in database as JSON
		String genreInDbAsJSON = this.mapper.writeValueAsString(genreInDb);
		
		//Get status created
		ResultMatcher matchStatus = status().isCreated();
		
		//Get body
		ResultMatcher matchBody = content().json(genreInDbAsJSON);
		
		//Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadAllArtists() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/artists/read");
		
		//Create genre object that should resemble the existing one on database
		Genre genre2 = new Genre(1L, 'Jazz', 'dd');
		
		//Create a list and add the object
		List<Genre> genresOnDb = new ArrayList<>();
		genresOnDb.add(genre);
		
		//Convert list into JSON format
		String genresOnDbAsJSON = this.mapper.writeValueAsString(genresOnDb);
		
		//Get status code OK(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(genresOnDbAsJSON);
		
		//Perform the request and assert the list displays the artists present on db
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadOneArtist() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/artists/read/1");
		
		//Create the artist resembling the one existing on db
		Artist artistOnDb = new Artist(1L, "Jack Montano");
		
		//Convert the object into JSON format
		String artistOnDbAsJSON = this.mapper.writeValueAsString(artistOnDb);
		
		//Get status code
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(artistOnDbAsJSON);
		
		//Perform the request and assert that the artist read is the one we request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testUpdateArtist() throws Exception {
		//Create artist with updated data
		Artist updatedArtist = new Artist("Jack Borderson");
		
		//Convert artist into JSON format
		String updatedArtistAsJSON = this.mapper.writeValueAsString(updatedArtist);
		
		//Build mock request
		RequestBuilder mockRequest =
								put("/artists/update/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(updatedArtistAsJSON);
		
		//Create artist object which resemble the updated one on db
		Artist updatedArtistOnDb = new Artist(1L, "Jack Borderson");
		
		//Convert artist as JSON format
		String updatedArtistOnDbAsJSON = this.mapper.writeValueAsString(updatedArtistOnDb); 
		
		//Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();
		
		//Get content
		ResultMatcher matchBody = content().json(updatedArtistOnDbAsJSON);
		
		//Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testDeleteArtist() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = delete("/artists/delete/1");
		
		//Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();
		
		//Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
