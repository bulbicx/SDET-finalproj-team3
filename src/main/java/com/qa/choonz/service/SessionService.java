package com.qa.choonz.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.SessionRepository;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.SessionDTO;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.utils.PasswordAuthentication;
import com.qa.choonz.utils.SessionTokens;

@Service
public class SessionService {
	
	private SessionRepository sessionRepo;
	private UserRepository userRepo;
	private ModelMapper mapper;
	private static final PasswordAuthentication passwordAuth = new PasswordAuthentication(10);
	private static final SessionTokens sessionToken = new SessionTokens();

	public SessionService(SessionRepository sessionRepo, UserRepository userRepo, ModelMapper mapper) {
		super();
		this.sessionRepo = sessionRepo;
		this.userRepo = userRepo;
		this.mapper = mapper;
	}

	private SessionDTO mapToDTO(Session session) {
		return this.mapper.map(session, SessionDTO.class);
	}

	
	public SessionDTO authenticate(User user) {
		User userFromDB = this.userRepo.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
		char[] pass = user.getPassword().toCharArray();
		// change exception
		if (passwordAuth.authenticate(pass, userFromDB.getPassword())) {	
			return createSession(userFromDB);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	public SessionDTO createSession(User userFromDB) {
		String token = sessionToken.newSessionToken();
		Session session = new Session();
		session.setToken(token);
		session.setUser(userFromDB);	
		return this.mapToDTO(this.sessionRepo.save(session));	
	}

	public boolean delete(String token) {
		Session session = this.sessionRepo.findByToken(token);
		this.sessionRepo.deleteById(session.getId());
		return !this.sessionRepo.existsById(session.getId());
	}
}
