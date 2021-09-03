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
import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.builder.AdminUserBuilder;
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
public class AdminUserControllerIntegrationTest {

	@Autowired
	private MockMvc mock;

	@Autowired
	private ObjectMapper mapper;// Convert to JSON

	@Test
	void testReadAllUsers() throws Exception {
		// Build mock request
		RequestBuilder mockRequest = get("/users/admin/read");
		// User
		// Create User object
		AdminUser user = new AdminUserBuilder().id(2L).name("John").username("polkaot").build();
		//
		// Create a list and add the object
		List<AdminUser> usersOnDb = new ArrayList<>();
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
		RequestBuilder mockRequest = get("/users/admin/read/2");

		// Create User object
		AdminUser userOnDb = new AdminUserBuilder().id(2L).name("John").username("polkaot").build();

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
		RequestBuilder mockRequest = delete("/users/admin/delete/2");

		// Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();

		// Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testCreateUser() throws Exception {
		// Create User object
		AdminUser user = new AdminUserBuilder().name("Micheal90").username("Michaelss").password("password123")
				.build();

//		// Convert it to a JSON String
		ObjectMapper mapper = new ObjectMapper();
		mapper.setAnnotationIntrospector(new IgnoreJacksonWriteOnlyAccess());
		String userAsJSON = mapper.writeValueAsString(user);

		// Build mock request
		RequestBuilder mockRequest = post("/users/admin/create").contentType(MediaType.APPLICATION_JSON)
				.content(userAsJSON);

		// Get status created
		ResultMatcher matchStatus = status().isCreated();

		// Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	@Test
	void testUpdateUser() throws Exception {
		// Create User object
		AdminUser user = new AdminUserBuilder().name("Micheal90").username("polkaot").password("password123")
				.build();

		// Convert it to a JSON String
		ObjectMapper mapper = new ObjectMapper();
		mapper.setAnnotationIntrospector(new IgnoreJacksonWriteOnlyAccess());
		String updatedUserAsJSON = mapper.writeValueAsString(user);

		// Build mock request
		RequestBuilder mockRequest = put("/users/admin/update/2").contentType(MediaType.APPLICATION_JSON)
				.content(updatedUserAsJSON);

		// Get status created
		ResultMatcher matchStatus = status().isAccepted();

		// Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

}
