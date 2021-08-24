package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Artist;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:sql-schema.sql", "classpath:sql-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class ArtistControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;//Convert to JSON
	
	@Test
	void testCreateArtist() throws Exception {
		//Create Artist object
		Artist artist = new Artist("Jack Montano");
		
		//Convert it to a JSON String
		String artistAsJSON = this.mapper.writeValueAsString(artist);
		
		//Build mock request
		RequestBuilder mockRequest =
								post("/artists/create")
								.contentType(MediaType.APPLICATION_JSON)
								.content(artistAsJSON);
		
		//Create an artist object resembling the one created in database
		Artist artistInDb = new Artist(2L, "Jack Montano");
		
		//Convert the artist in database as JSON
		String artistInDbAsJSON = this.mapper.writeValueAsString(artistInDb);
		
		//Get status created
		ResultMatcher matchStatus = status().isCreated();
		
		//Get body
		ResultMatcher matchBody = content().json(artistInDbAsJSON);
		
		//Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadAllArtists() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/artists/read");
		
		//Create artist object that should resemble the existing one on database
		Artist artist = new Artist(1L, "Jack Montano");
		
		//Create a list and add the object
		List<Artist> artistsOnDb = new ArrayList<>();
		artistsOnDb.add(artist);
		
		//Convert list into JSON format
		String artistsOnDbAsJSON = this.mapper.writeValueAsString(artistsOnDb);
		
		//Get status code OK(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(artistsOnDbAsJSON);
		
		//Perform the request and assert the list displays the artists present on db
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadOneArtist() {
		
	}
	
	@Test
	void testUpdateArtist() {
		
	}
	
	@Test
	void testDeleteArtist() {
		
	}
	
}
