package com.qa.choonz.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.utils.PasswordAuthentication;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class UserService {

	private UserRepository repo;
	private ModelMapper mapper;
	private static final PasswordAuthentication passwordAuth = new PasswordAuthentication(10);

	public UserService(UserRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private UserDTO mapToDTO(User user) {
		return this.mapper.map(user, UserDTO.class);
	}

	public UserDTO create(User user) {
		char[] pass = user.getPassword().toCharArray();
		user.setPassword(passwordAuth.hash(pass));
		User created = this.repo.save(user);
		return this.mapToDTO(created);
	}

	public List<UserDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public UserDTO read(long id) {
		User found = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		return this.mapToDTO(found);
	}

	public UserDTO update(User user, long id) {
		User toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		toUpdate.setName(user.getName());
		toUpdate.setUsername(user.getUsername());
		PasswordAuthentication hashPass = new PasswordAuthentication();
		char[] pass = user.getPassword().toCharArray();
		toUpdate.setPassword(hashPass.hash(pass));
		toUpdate.setPlaylists(user.getPlaylists());
		User updated = this.repo.save(toUpdate);
		return this.mapToDTO(updated);

	}

	public boolean delete(long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	
}
