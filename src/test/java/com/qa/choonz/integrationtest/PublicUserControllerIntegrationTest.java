package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
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
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.builder.AlbumBuilder;
import com.qa.choonz.persistence.domain.builder.GenreBuilder;
import com.qa.choonz.persistence.domain.builder.PlaylistBuilder;
import com.qa.choonz.persistence.domain.builder.PublicUserBuilder;
import com.qa.choonz.persistence.domain.builder.TrackBuilder;
import com.qa.choonz.utils.IgnoreJacksonWriteOnlyAccess;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:sql-schema.sql",
		"classpath:sql-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PublicUserControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;// Convert to JSON


	@Test
	void testReadAllUsers() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = get("/users/public/read");
		

		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Playlist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		// User
		// Create User object
		PublicUser user = new PublicUserBuilder().id(1L).name("Micheal").username("polkadot")
						.build();
		// Artist
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		// Genre
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").image(image).build();
		// Album
		Album album = new AlbumBuilder().id(1L).cover(image).name("Blackpool").artist(artist).genre(genre).build();
		// track` (`duration`, `lyrics`, `name`, `album_id`) VALUES (180, 'la la la
		// land', 'Parkour', 1);
		Track track = new TrackBuilder().duration(180).lyrics("la la la land").name("Parkour").album(album).build();
		// Add list of tracks to album
		List<Track> tracklist = new ArrayList<Track>();
		tracklist.add(track);
		album.setTracks(tracklist);
		List<Album> albums = new ArrayList<Album>();
		albums.add(album);
		artist.setAlbums(albums);

		Playlist playlist = new PlaylistBuilder().id(1L).name("My playlist").artwork(image)
				.description("The best playlist").user(user).tracks(tracklist).build();

		// Create a list and add the object
		List<Playlist> playlistsOnDb = new ArrayList<>();
		playlistsOnDb.add(playlist);

		//Add playlist to user
		user.setPlaylists(playlistsOnDb);
		
		// Create a list and add the object
		List<PublicUser> usersOnDb = new ArrayList<>();
		usersOnDb.add(user);

		// Convert list into JSON format
		String usersOnDbAsJSON = this.mapper.writeValueAsString(usersOnDb);

		// Get status code OK(200)
		ResultMatcher matchStatus = status().isOk();

		// Get body
		ResultMatcher matchBody = content().json(usersOnDbAsJSON);

		// Perform the request and assert the list displays the artists present on db
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadOneUser() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = get("/users/public/read/1");

		// Create the user object resembling the one existing on db
		PublicUser userOnDb = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(),
				new ArrayList<>());

		// Convert the object into JSON format
		String userOnDbAsJSON = this.mapper.writeValueAsString(userOnDb);

		// Get status code
		ResultMatcher matchStatus = status().isOk();

		// Get body
		ResultMatcher matchBody = content().json(userOnDbAsJSON);

		// Perform the request and assert that the user read is the one we request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteUser() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = delete("/users/public/delete/1");

		// Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();

		// Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	@Test
	void testCreateUser() throws Exception {
		// Create User object
		PublicUser user = new PublicUserBuilder().name("Micheal90").username("Michaels").password("password123")
				.playlists(new ArrayList<>()).build();

//		// Convert it to a JSON String
		ObjectMapper mapper = new ObjectMapper();
		mapper.setAnnotationIntrospector(new IgnoreJacksonWriteOnlyAccess());
		String userAsJSON = mapper.writeValueAsString(user);

		// Build mock request
		RequestBuilder mockRequest = post("/users/public/create").contentType(MediaType.APPLICATION_JSON)
				.content(userAsJSON);

		// Get status created
		ResultMatcher matchStatus = status().isCreated();

		// Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
	
	@Test
	void testUpdateUser() throws Exception {
		// Create User object
		PublicUser user = new PublicUserBuilder().name("Micheal90").username("Michaels").password("password123")
				.playlists(new ArrayList<>()).build();

		// Convert it to a JSON String
		ObjectMapper mapper = new ObjectMapper();
		mapper.setAnnotationIntrospector(new IgnoreJacksonWriteOnlyAccess());
		String updatedUserAsJSON = mapper.writeValueAsString(user);

		// Build mock request
		RequestBuilder mockRequest = put("/users/public/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(updatedUserAsJSON);

		// Get status created
		ResultMatcher matchStatus = status().isAccepted();

		// Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

}
