package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Image;

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
		File file = new File("src/test/resources/images/testimage.png");
		byte[] byteImage = readFileToByteArray(file);
		// Create Image object
		Image image = new Image(2L, "testimage.png", "image/png", byteImage);
		//Create Artist object like in db
		Artist artist = new Artist(2L, "Jack Montano 2", new ArrayList<>(), image);
		
		// Convert it to a JSON String
		String artistAsJson = this.mapper.writeValueAsString(artist);

		// Get status created
		ResultMatcher matchStatus = status().isCreated();

		// Get body
		ResultMatcher matchBody = content().json(artistAsJson);

		String endpoint = "/artists/create";

		
		MockMultipartFile firstFile = new MockMultipartFile("file", "testimage.png", MediaType.IMAGE_PNG_VALUE, byteImage);

		this.mock.perform(MockMvcRequestBuilders.multipart(endpoint)
				.file(firstFile)
				.param("name", "Jack Montano 2")
				.param("token", "$31$11$Zhi4PT548-kYfpgwiOM8aE0EkCLkyHOQuKyUI_S1Fb0")
				)
		.andExpect(matchBody)
		.andExpect(matchStatus);	
	}
	
	@Test
	void testReadAllArtists() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/artists/read");
		
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Artist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		//Create Artist object like in db
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		
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
	void testReadOneArtist() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/artists/read/1");
		
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		//Create Artist object like in db
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		
		//Convert the object into JSON format
		String artistOnDbAsJSON = this.mapper.writeValueAsString(artist);
		
		//Get status code
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(artistOnDbAsJSON);
		
		//Perform the request and assert that the artist read is the one we request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testUpdateArtist() throws Exception {
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		//Create Artist object like in db
		Artist artist = new Artist(1L, "Jack Borderson", new ArrayList<>(), image);
		
		//Convert artist into JSON format
		String updatedArtistAsJSON = this.mapper.writeValueAsString(artist);
		
		//Build mock request
		RequestBuilder mockRequest =
								put("/artists/update/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(updatedArtistAsJSON);
		
		//Create artist object which resemble the updated one on db
		Artist updatedArtistOnDb = new Artist(1L, "Jack Borderson", new ArrayList<>(), image);
		
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
	
	private static byte[] readFileToByteArray(File file) {
		FileInputStream fis = null;
		// Creating a byte array using the length of the file
		// file.length returns long which is cast to int
		byte[] bArray = new byte[(int) file.length()];
		try {
			fis = new FileInputStream(file);
			fis.read(bArray);
			fis.close();
		} catch (IOException ioExp) {
			ioExp.printStackTrace();
		}
		return bArray;
	}
}
