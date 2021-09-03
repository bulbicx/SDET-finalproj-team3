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
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.builder.AlbumBuilder;
import com.qa.choonz.persistence.domain.builder.GenreBuilder;
import com.qa.choonz.persistence.domain.builder.TrackBuilder;

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
		File file = new File("src/test/resources/images/testimage.png");
		byte[] byteImage = readFileToByteArray(file);
		// Create Image object
		Image image = new Image(2L, "testimage.png", "image/png", byteImage);
		Genre genre = new GenreBuilder()
				.id(2L)
				.name("hip hop")
				.description("hip hop genre")
				.image(image)
				.build();
		
		// Convert it to a JSON String
		String genreAsJson = this.mapper.writeValueAsString(genre);

		// Get status created
		ResultMatcher matchStatus = status().isCreated();

		// Get body
		ResultMatcher matchBody = content().json(genreAsJson);

		String endpoint = "/genres/create";

		
		MockMultipartFile firstFile = new MockMultipartFile("file", "testimage.png", MediaType.IMAGE_PNG_VALUE, byteImage);

		this.mock.perform(MockMvcRequestBuilders.multipart(endpoint)
				.file(firstFile)
				.param("name", "hip hop")
				.param("description", "hip hop genre")
				.param("token", "$31$11$Zhi4PT548-kYfpgwiOM8aE0EkCLkyHOQuKyUI_S1Fb0")
				)
		.andExpect(matchBody)
		.andExpect(matchStatus);	
	}
	
	@Test
	void testReadAllGenres() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/genres/read");
		
		//Create genre object that should resemble the existing one on database
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").image(image).build();
		
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
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Genre object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		Genre genreOnDb = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").image(image).build();
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new AlbumBuilder().id(1L).name("Blackpool").artist(artist).genre(genreOnDb).cover(image).build();
		List<Album> albumList = new ArrayList<>();
		albumList.add(album);
		genreOnDb.setAlbums(albumList);
		
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
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Album object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new AlbumBuilder().id(1L).name("Blackpool").artist(artist).cover(image).build();
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(), new ArrayList<>());
		Playlist playlist = new Playlist(1L, "My playlist", "The best playlist", image, new ArrayList<>(), user);
		List<Playlist> playlists = new ArrayList<>();
		playlists.add(playlist);
		List<Playlist> playlistsEmpty = new ArrayList<>();
		Track track = new Track(1L, "Parkour", album, playlists, 180, "la la la land");
		Track track2 = new TrackBuilder()
				.id(50L)
				.name("Parkour 2")
				.album(album)
				.duration(210)
				.lyrics("la land")
				.playlists(playlistsEmpty)
				.build();
		List<Track> trackList = new ArrayList<>();
		trackList.add(track);
		trackList.add(track2);
		album.setTracks(trackList);
		List<Album> albumList = new ArrayList<>();
		albumList.add(album);
		artist.setAlbums(albumList);
		Genre updatedGenre = new GenreBuilder().id(1L).name("new Jazz").description("new Jazz genre").image(image).albums(albumList).build();
		
		Genre genreToUpdate = new GenreBuilder().name("new Jazz").description("new Jazz genre").build();
		
		String genreToUpdateAsJSON = this.mapper.writeValueAsString(genreToUpdate);
		//Build mock request
		RequestBuilder mockRequest =
								put("/genres/update/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(genreToUpdateAsJSON);
		
		
		
		
		//Convert genre as JSON format
		String updatedGenreOnDbAsJSON = this.mapper.writeValueAsString(updatedGenre); 
		
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
