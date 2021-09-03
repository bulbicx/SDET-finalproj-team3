package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.builder.AlbumBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:sql-schema.sql",
		"classpath:sql-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class AlbumControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreateAlbum() throws Exception {
		File file = new File("src/test/resources/images/testimage.png");
		byte[] byteImage = readFileToByteArray(file);
		byte[] byteImage2 = new byte[1];
		byteImage2[0] = 'W';
		// Create Album object
		Image image = new Image(2L, "testimage.png", "image/png", byteImage);
		Image image2 = new Image(1L, "johnpng", "png", byteImage2);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image2);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image2);

		// Create an album object resembling the one created in database
		Album albumInDb = new AlbumBuilder().id(2L).name("Blackpool two").artist(artist).genre(genre).cover(image).build();

		// Convert it to a JSON String
		String albumAsJSON = this.mapper.writeValueAsString(albumInDb);

		// Get status created
		ResultMatcher matchStatus = status().isCreated();

		// Get body
		ResultMatcher matchBody = content().json(albumAsJSON);

		String endpoint = "/albums/create/artist/1/genre/1";

		
		MockMultipartFile firstFile = new MockMultipartFile("file", "testimage.png", MediaType.IMAGE_PNG_VALUE, byteImage);

		this.mock.perform(MockMvcRequestBuilders.multipart(endpoint)
				.file(firstFile)
				.param("name", "Blackpool two")
				.param("token", "$31$11$Zhi4PT548-kYfpgwiOM8aE0EkCLkyHOQuKyUI_S1Fb0")
				)
		.andExpect(matchBody)
		.andExpect(matchStatus);				
	}

	@Test
	void testReadAllAlbums() throws Exception {

		RequestBuilder mockRequest = get("/albums/read");

		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new AlbumBuilder().id(1L).name("Blackpool").artist(artist).genre(genre).cover(image).build();

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
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new AlbumBuilder().id(1L).name("Blackpool").artist(artist).genre(genre).cover(image).build();

		String albumAsJSON = this.mapper.writeValueAsString(album);

		ResultMatcher matchStatus = status().isOk();

		ResultMatcher matchBody = content().json(albumAsJSON);

		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);

	}

	@Test
	void testUpdateAlbum() throws Exception {
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album updatedAlbum = new AlbumBuilder().id(1L).name("Bluepool").artist(artist).genre(genre).cover(image)
				.build();

		// Convert album into JSON format
		String updatedAlbumAsJSON = this.mapper.writeValueAsString(updatedAlbum);

		// Build mock request
		RequestBuilder mockRequest = put("/albums/update/1/1/1").contentType(MediaType.APPLICATION_JSON)
				.content(updatedAlbumAsJSON);

		// Create artist object which resemble the updated one on db
		Album updatedAlbumOnDb = new AlbumBuilder().id(1L).name("Bluepool").artist(artist).genre(genre).cover(image)
				.build();

		// Convert artist as JSON format
		String updatedAlbumOnDbAsJSON = this.mapper.writeValueAsString(updatedAlbumOnDb);

		// Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();

		// Get content
		ResultMatcher matchBody = content().json(updatedAlbumOnDbAsJSON);

		// Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteAlbum() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = delete("/albums/delete/1");

		// Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();

		// Build the mock request
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
