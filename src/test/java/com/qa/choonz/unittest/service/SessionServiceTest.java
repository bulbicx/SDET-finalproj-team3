package com.qa.choonz.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.repository.SessionRepository;
import com.qa.choonz.persistence.repository.PublicUserRepository;
import com.qa.choonz.service.SessionService;

@SpringBootTest
public class SessionServiceTest {
	
	@MockBean
	private SessionRepository repo;
	
	@MockBean
	private PublicUserRepository userRepo;
	
	@Autowired
	private SessionService service;
	
	private PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>());
	private Optional<PublicUser> optionalUser = Optional.of(new PublicUser(0L, "username", "real name", "password", new ArrayList<>()));
	private Session session = new Session(0L, user, "token");
	
	@Test
	public void SessionAuthTest() {
		Mockito.when(this.userRepo.findByUsername("username")).thenReturn(optionalUser);
		Mockito.when(this.repo.save(session)).thenReturn(session);
		
		assertThat(session).isEqualTo(service.authenticate(user));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(session);
		Mockito.verify(this.userRepo, Mockito.times(1)).findByUsername("username");
		
	}
	
	@Test
	public void CreateSessionTest() {
		Mockito.when(this.repo.save(session)).thenReturn(session);
		
		assertThat(session).isEqualTo(service.createSession(user));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(session);
	}

}
