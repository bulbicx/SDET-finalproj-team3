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
import com.qa.choonz.persistence.domain.PublicUser;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Sql(scripts = {"classpath:sql-schema.sql", "classpath:sql-data.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UserControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;//Convert to JSON
	
	//the main issue here is passwords and being able to access them
	
	
	@Test
	void testCreateUser() throws Exception {
		//Create User object
		PublicUser user = new PublicUser("Micheal90", "Micheal", "password123");
		
		//Convert it to a JSON String
		String userAsJSON = this.mapper.writeValueAsString(user);
		
		//Build mock request
		RequestBuilder mockRequest =
								post("/users/create")
								.contentType(MediaType.APPLICATION_JSON)
								.content(userAsJSON);
		
		//Create a User object resembling the one created in database
		PublicUser userInDb = new PublicUser(2L, "Micheal90", "Micheal", "password123");
		
		//Convert the user resembling the one in database as JSON
		String userInDbAsJSON = this.mapper.writeValueAsString(userInDb);
		
		//Get status created
		ResultMatcher matchStatus = status().isCreated();
		
		//Get body
		ResultMatcher matchBody = content().json(userInDbAsJSON);
		
		//Build the request and assert it is what we have created
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadAllUsers() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/users/read");
		
		//Create user object that should resemble the existing one on database
		PublicUser user = new PublicUser(1L, "polkadot", "Micheal", "password123");
		
		//Create a list and add the object
		List<PublicUser> usersOnDb = new ArrayList<>();
		usersOnDb.add(user);
		
		//Convert list into JSON format
		String usersOnDbAsJSON = this.mapper.writeValueAsString(usersOnDb);
		
		//Get status code OK(200)
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(usersOnDbAsJSON);
		
		//Perform the request and assert the list displays the artists present on db
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testReadOneUser() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = get("/users/read/1");
		
		//Create the user object resembling the one existing on db
		PublicUser userOnDb = new PublicUser(1L,"polkadot", "Micheal", "password123");
		
		//Convert the object into JSON format
		String userOnDbAsJSON = this.mapper.writeValueAsString(userOnDb);
		
		//Get status code
		ResultMatcher matchStatus = status().isOk();
		
		//Get body
		ResultMatcher matchBody = content().json(userOnDbAsJSON);
		
		//Perform the request and assert that the user read is the one we request
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testUpdateUser() throws Exception {
		//Create user object with updated data
		PublicUser updatedUser = new PublicUser("polkadotNew", "MichealNew", "password123New");
		
		//Convert user into JSON format
		String updatedUserAsJSON = this.mapper.writeValueAsString(updatedUser);
		
		//Build mock request
		RequestBuilder mockRequest =
								put("/users/update/1")
								.contentType(MediaType.APPLICATION_JSON)
								.content(updatedUserAsJSON);
		
		//Create user object which resemble the updated one on db
		PublicUser updatedUserOnDb = new PublicUser(1L,"polkadotNew", "MichealNew", "password123New" );
		
		//Convert user as JSON format
		String updatedUserOnDbAsJSON = this.mapper.writeValueAsString(updatedUserOnDb); 
		
		//Get status code(202)
		ResultMatcher matchStatus = status().isAccepted();
		
		//Get content
		ResultMatcher matchBody = content().json(updatedUserOnDbAsJSON);
		
		//Perform the request and assert that the update has been successful
		this.mock.perform(mockRequest).andExpect(matchBody).andExpect(matchStatus);
	}
	
	@Test
	void testDeleteUser() throws Exception {
		//Build mock request
		RequestBuilder mockRequest = delete("/users/delete/1");
		
		//Check status code(204)
		ResultMatcher matchStatus = status().isNoContent();
		
		//Build the mock request
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

}
