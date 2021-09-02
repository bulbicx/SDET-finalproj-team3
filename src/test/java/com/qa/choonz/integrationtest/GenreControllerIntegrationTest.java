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
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.builder.GenreBuilder;

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
	void testCreateGenre() throws Exception {
		//Create Genre object
		Genre genre = new GenreBuilder().name("hip hop").description("hip hop genre").build();
		
		//Convert it to a JSON String
		String genreAsJSON = this.mapper.writeValueAsString(genre);
		
		//Build mock request
		RequestBuilder mockRequest =
								post("/genres/create")
								.contentType(MediaType.APPLICATION_JSON)
								.content(genreAsJSON);
		
		//Create an Genre object resembling the one created in database
		Genre genreInDb = new GenreBuilder().id(2L).name("hip hop").description("hip hop genre").build();
		
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
	void testReadAllGenres() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/genres/read");
		
		//Create genre object that should resemble the existing one on database
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").build();
		
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
	void testReadOneGenre() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/genres/read/1");
		
		//Create the genre object resembling the one existing on db
		Genre genreOnDb = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").build();
		
		//Convert the object into JSON format
		String genreOnDbAsJSON = this.mapper.writeValueAsString(genreOnDb);
		
		//Get status code
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(genreOnDbAsJSON);
		
		//Perform the request and assert that the artist read is the one we request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testUpdateGenre() throws Exception {
		//Create genre object with updated data
		Genre updatedGenre = new GenreBuilder().name("new Jazz").description("new Jazz genre").build();
		
		//Convert genre into JSON format
		String updatedGenreAsJSON = this.mapper.writeValueAsString(updatedGenre);
		
		//Build mock request
		RequestBuilder mockRequest =
								put("/genres/update/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(updatedGenreAsJSON);
		
		//Create genre object which resemble the updated one on db
		Genre updatedGenreOnDb = new GenreBuilder().name("new Jazz").description("new Jazz genre").build();
		
		//Convert genre as JSON format
		String updatedGenreOnDbAsJSON = this.mapper.writeValueAsString(updatedGenreOnDb); 
		
		//Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();
		
		//Get content
		ResultMatcher matchBody = content().json(updatedGenreOnDbAsJSON);
		
		//Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testDeleteGenre() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = delete("/genres/delete/1");
		
		//Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();
		
		//Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
