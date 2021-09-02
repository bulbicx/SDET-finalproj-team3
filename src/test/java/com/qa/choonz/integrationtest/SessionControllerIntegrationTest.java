package com.qa.choonz.integrationtest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
public class SessionControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	
	@Autowired
	private ObjectMapper mapper;//Convert to JSON
	
	//session is set up kinda weird with passing in a user but expecting a session
	
	@Test
	void testAuthenticateSession() throws Exception {
		
		PublicUser user = new PublicUser("Micheal90", "Micheal", "password123");
		
		//Convert it to a JSON String
		String userAsJSON = this.mapper.writeValueAsString(user);
		
		RequestBuilder mockRequest = post("/sessions/authenticate")
									.contentType(MediaType.APPLICATION_JSON)
									.content(userAsJSON);
		
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
	void testDeleteSession() throws Exception {
		RequestBuilder mockRequest = delete("/sessions/delete/token-name");
		
		ResultMatcher matchStatus = status().isNoContent();
		
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

}
