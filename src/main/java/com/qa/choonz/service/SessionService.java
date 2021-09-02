package com.qa.choonz.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.repository.SessionRepository;
import com.qa.choonz.persistence.repository.AdminUserRepository;
import com.qa.choonz.persistence.repository.PublicUserRepository;
import com.qa.choonz.rest.dto.SessionDTO;
import com.qa.choonz.rest.dto.PublicUserDTO;
import com.qa.choonz.utils.PasswordAuthentication;
import com.qa.choonz.utils.SessionTokens;

@Service
public class SessionService {
	
	private SessionRepository sessionRepo;
	private PublicUserRepository publicUserRepo;
	private AdminUserRepository adminUserRepo;
	
	private ModelMapper mapper;
	private static final PasswordAuthentication passwordAuth = new PasswordAuthentication(10);
	private static final SessionTokens sessionToken = new SessionTokens();

	public SessionService(
			SessionRepository sessionRepo, 
			PublicUserRepository publicUserRepo, 
			ModelMapper mapper, 
			AdminUserRepository adminUserRepo) {
		super();
		this.sessionRepo = sessionRepo;
		this.adminUserRepo = adminUserRepo;
		this.publicUserRepo = publicUserRepo;
		this.mapper = mapper;
	}

	private SessionDTO mapToDTO(Session session) {
		return this.mapper.map(session, SessionDTO.class);
	}

	
	public SessionDTO authenticate(AdminUser user) {
		
		AdminUser userFromDB = this.adminUserRepo.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
		char[] pass = user.getPassword().toCharArray();
		// change exception
		if (passwordAuth.authenticate(pass, userFromDB.getPassword())) {	
			return createSession(userFromDB);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	public SessionDTO authenticate(PublicUser user) {
		
		PublicUser userFromDB = this.publicUserRepo.findByUsername(user.getUsername()).orElseThrow(UserNotFoundException::new);
		char[] pass = user.getPassword().toCharArray();
		// change exception
		if (passwordAuth.authenticate(pass, userFromDB.getPassword())) {	
			return createSession(userFromDB);
		} else {
			throw new UserNotFoundException();
		}
	}
	
	public SessionDTO createSession(AdminUser userFromDB) {
		String token = sessionToken.newSessionToken();
		Session session = new Session();
		session.setToken(token);
		session.setUser(userFromDB);	
		return this.mapToDTO(this.sessionRepo.save(session));	
	}
	
	public SessionDTO createSession(PublicUser userFromDB) {
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
