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
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.domain.builder.TrackBuilder;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:sql-schema.sql", "classpath:sql-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class TrackControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Test
	void testCreateTrack() throws Exception {
		
		//Create track object
		Image image = new Image(0L, "image name", "image type", null);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new Album(1L, "Blackpool", new ArrayList<>(), artist, genre,  image);
		User user = new User(1L, "polkadot", "Micheal", "password123", new ArrayList<>());
		Playlist playlist = new Playlist(1L, "My playlist", "The best playlist", image, new ArrayList<>(), user);
		List<Playlist> playlists = new ArrayList<>();
		playlists.add(playlist);
		Track track = new Track(1L, "Parkour", album, new ArrayList<>(), 180, "la la la land");
		
		//Convert it to a JSON String
		String trackAsJSON = this.mapper.writeValueAsString(track);
		
		//Build mock request
		RequestBuilder mockRequest =
								post("/tracks/create/album/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(trackAsJSON);
		
		//Create an track object resembling the one created in database
		Track trackInDb = new Track(1L, "Parkour", album, new ArrayList<>(), 180, "la la la land");
		
		//Convert the track in database as JSON
		String trackInDbAsJSON = this.mapper.writeValueAsString(trackInDb);
		
		//Get status created
		ResultMatcher matchStatus = status().isCreated();
		
		//Get body
		ResultMatcher matchBody = content().json(trackInDbAsJSON);
		
		//Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadAllTracks() throws Exception {
		
		RequestBuilder mockRequest = get("/tracks/read");
		
		Image image = new Image(0L, "image name", "image type", null);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new Album(1L, "Blackpool", new ArrayList<>(), artist, genre, image);
		User user = new User(1L, "polkadot", "Micheal", "password123", new ArrayList<>());
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
		
		List<Track> tracksInDb = new ArrayList<>();
		tracksInDb.add(track);
		tracksInDb.add(track2);
		
		String tracksOnDbAsJSON = this.mapper.writeValueAsString(tracksInDb);
		
		ResultMatcher matchStatus = status().isOk();
		
		ResultMatcher matchBody = content().json(tracksOnDbAsJSON);
		
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
		
	}
	
	@Test
	void testReadOneTrack() throws Exception {
		
		RequestBuilder mockRequest = get("/tracks/read/1");
		
		Image image = new Image(0L, "image name", "image type", null);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new Album(1L, "Blackpool", new ArrayList<>(), artist, genre, image);
		User user = new User(1L, "polkadot", "Micheal", "password123", new ArrayList<>());
		Playlist playlist = new Playlist(1L, "My playlist", "The best playlist", image, new ArrayList<>(), user);
		List<Playlist> playlists = new ArrayList<>();
		playlists.add(playlist);
		Track track = new Track(1L, "Parkour", album, playlists, 180, "la la la land");
		
		String trackAsJSON = this.mapper.writeValueAsString(track);
		
		ResultMatcher matchStatus = status().isOk();
		
		ResultMatcher matchBody = content().json(trackAsJSON);
		
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
		
	}
	
	@Test
	void testUpdateTrack() throws Exception {
		//Create track with updated data
		Image image = new Image(0L, "image name", "image type", null);
		Genre genre = new Genre(1L, "Jazz", "Jazz genre", new ArrayList<>(), image);
		Artist artist = new Artist(1L, "Jack Montano", new ArrayList<>(), image);
		Album album = new Album(1L, "Blackpool", new ArrayList<>(), artist, genre, image);
		User user = new User(1L, "polkadot", "Micheal", "password123", new ArrayList<>());
		Playlist playlist = new Playlist(1L, "My playlist", "The best playlist", image, new ArrayList<>(), user);
		List<Playlist> playlists = new ArrayList<>();
		playlists.add(playlist);
		Track updateTrack = new Track(1L, "Parkourrr", album, playlists, 280, "la la la laaaaaa land");
		
		//Convert track into JSON format
		String updateTrackAsJSON = this.mapper.writeValueAsString(updateTrack);
		
		//Build mock request
		RequestBuilder mockRequest =
								put("/tracks/update/1/album/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(updateTrackAsJSON);
		
		//Create track object which resemble the updated one on db
		Track updateTrackInDb = new Track(1L, "Parkourrr", album, playlists, 280, "la la la laaaaaa land");
		
		//Convert track as JSON format
		String updateTrackInDbAsJSON = this.mapper.writeValueAsString(updateTrackInDb); 
		
		//Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();
		
		//Get content
		ResultMatcher matchBody = content().json(updateTrackInDbAsJSON);
		
		//Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testDeleteTrack() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = delete("/tracks/delete/1");
		
		//Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();
		
		//Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
