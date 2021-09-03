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
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.builder.AlbumBuilder;
import com.qa.choonz.persistence.domain.builder.GenreBuilder;
import com.qa.choonz.persistence.domain.builder.PlaylistBuilder;
import com.qa.choonz.persistence.domain.builder.TrackBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = { "classpath:sql-schema.sql",
		"classpath:sql-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class PlaylistControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;// Convert to JSON

	@Test
	void testCreatePlaylist() throws Exception {
		File file = new File("src/test/resources/images/testimage.png");
		byte[] byteImage = readFileToByteArray(file);
		// Create Image object
		Image image = new Image(2L, "testimage.png", "image/png", byteImage);
		Playlist playlist = new PlaylistBuilder().id(2L).name("Summer").artwork(image).description("Summer bangers").build();
		// UserId
		Long userId = 2L;

		// Convert it to a JSON String
		String playlistAsJSON = this.mapper.writeValueAsString(playlist);

		// Get status created
		ResultMatcher matchStatus = status().isCreated();

		// Get body
		ResultMatcher matchBody = content().json(playlistAsJSON);

		String endpoint = "/playlists/create/user/" + userId;

		MockMultipartFile firstFile = new MockMultipartFile("file", "testimage.png", MediaType.IMAGE_PNG_VALUE,
				byteImage);

		this.mock.perform(MockMvcRequestBuilders.multipart(endpoint)
						.file(firstFile)
						.param("name", "Summer")
						.param("description","Summer bangers")
						.param("token", "$31$11$eTW7By3kk0_UdPsmwrFWvyLLKwtEGDd-tVnSgrIJQ4Q"))
				.andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadAllPlaylists() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = get("/playlists/read");
		
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Playlist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		// User
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(),
				new ArrayList<>());
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

		// Convert list into JSON format
		String playlistsOnDbAsJSON = this.mapper.writeValueAsString(playlistsOnDb);

		// Get status code OK(200)
		ResultMatcher matchStatus = status().isOk();

		// Get body
		ResultMatcher matchBody = content().json(playlistsOnDbAsJSON);

		// Perform the request and assert the list displays the artists present on db
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testReadOnePlaylist() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = get("/playlists/read/1");
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Playlist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		// User
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(),
				new ArrayList<>());
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>() ,image);
		// Genre
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").build();
		// Album
		Album album = new AlbumBuilder().id(1L).cover(image).name("Blackpool").artist(artist).genre(genre).build();

		Track track = new TrackBuilder().duration(180).lyrics("la la la land").name("Parkour").album(album).build();
		// Add list of tracks to album
		List<Track> tracklist = new ArrayList<Track>();
		tracklist.add(track);
		album.setTracks(tracklist);

		Playlist playlist = new PlaylistBuilder().id(1L).name("My playlist").artwork(image)
				.description("The best playlist").user(user).tracks(tracklist).build();

		// Convert into JSON format
		String playlistOnDbAsJSON = this.mapper.writeValueAsString(playlist);

		// Get status code OK(200)
		ResultMatcher matchStatus = status().isOk();

		// Get body
		ResultMatcher matchBody = content().json(playlistOnDbAsJSON);

		// Perform the request and assert the list displays the artists present on db
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testUpdatePlaylist() throws Exception {
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Playlist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		// User
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(),
				new ArrayList<>());
		//Artist
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>() ,image);
		// Genre
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").build();
		// Album
		Album album = new AlbumBuilder().id(1L).cover(image).name("Blackpool").artist(artist).genre(genre).build();
		// track` (`duration`, `lyrics`, `name`, `album_id`) VALUES (180, 'la la la
		// land', 'Parkour', 1);
		Track track = new TrackBuilder().duration(180).lyrics("la la la land").name("Parkour").album(album).build();
		// Add list of tracks to album
		List<Track> tracklist = new ArrayList<Track>();
		tracklist.add(track);
		album.setTracks(tracklist);

		Playlist playlist = new PlaylistBuilder().id(1L).name("My updated playlist").artwork(image)
				.description("The best playlist").user(user).tracks(tracklist).build();

		// Convert artist into JSON format
		String updatedPlaylistAsJSON = this.mapper.writeValueAsString(playlist);

		// Playlist to send as JSON
		Playlist playlistForPut = new PlaylistBuilder().id(1L).name("My updated playlist")
				.description("The best playlist").build();

		String playlistForPutAsJSON = this.mapper.writeValueAsString(playlistForPut);

		// Build mock request
		RequestBuilder mockRequest = put("/playlists/update/1").contentType(MediaType.APPLICATION_JSON)
				.content(playlistForPutAsJSON);

		// Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();

		// Get content
		ResultMatcher matchBody = content().json(updatedPlaylistAsJSON);

		// Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeletePlaylust() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = delete("/playlists/delete/1");

		// Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();

		// Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testPlaylistAddTrack() throws Exception {
		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Playlist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		// User
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(),
				new ArrayList<>());
		// Artist
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		// Genre
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").build();
		// Album
		Album album = new AlbumBuilder().id(1L).cover(image).name("Blackpool").artist(artist).genre(genre).build();
		// track` (`duration`, `lyrics`, `name`, `album_id`) VALUES (180, 'la la la
		// land', 'Parkour', 1);
		Track track = new TrackBuilder().id(1L).duration(180).lyrics("la la la land").name("Parkour").album(album)
				.build();
		Track track2 = new TrackBuilder().id(50L).duration(210).lyrics("la la land").name("Parkour 2").album(album)
				.build();
		// Add list of tracks to album
		List<Track> tracklist = new ArrayList<Track>();
		tracklist.add(track);
		tracklist.add(track2);
		album.setTracks(tracklist);

		Playlist playlist = new PlaylistBuilder().id(1L).name("My playlist").artwork(image)
				.description("The best playlist").user(user).tracks(tracklist).build();

		// Convert playlist into JSON format
		String updatedPlaylistAsJSON = this.mapper.writeValueAsString(playlist);

		// Playlist id
		Long playlistId = 1L;

		// Track id
		Long trackId = 50L;

		// Build mock request
		RequestBuilder mockRequest = put("/playlists/" + playlistId + "/addTrack/" + trackId);

		// Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();

		// Get content
		ResultMatcher matchBody = content().json(updatedPlaylistAsJSON);

		// Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}

	@Test
	void testDeleteTrackFromPlaylist() throws Exception {

		byte[] byteImage = new byte[1];
		byteImage[0] = 'W';
		// Create Playlist object
		Image image = new Image(1L, "johnpng", "png", byteImage);
		// User
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123", new ArrayList<>(),
				new ArrayList<>());
		// Artist
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		// Genre
		Genre genre = new GenreBuilder().id(1L).name("Jazz").description("Jazz genre").build();
		// Album
		Album album = new AlbumBuilder().id(1L).cover(image).name("Blackpool").artist(artist).genre(genre).build();
		// Add list of tracks to album
		List<Track> tracklist = new ArrayList<Track>();
		album.setTracks(tracklist);

		Playlist playlist = new PlaylistBuilder().id(1L).name("My playlist").artwork(image)
				.description("The best playlist").user(user).tracks(tracklist).build();

		// Convert playlist into JSON format
		String updatedPlaylistAsJSON = this.mapper.writeValueAsString(playlist);

		Long playlistId = 1L;
		Long trackId = 1L;

		// Build mock request
		RequestBuilder mockRequest = put("/playlists/" + playlistId + "/removeTrack/" + trackId);

		// Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();

		// Get content
		ResultMatcher matchBody = content().json(updatedPlaylistAsJSON);

		// Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
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
