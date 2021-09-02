package com.qa.choonz.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.repository.AdminUserRepository;
import com.qa.choonz.persistence.repository.PublicUserRepository;
import com.qa.choonz.rest.dto.AdminUserDTO;
import com.qa.choonz.rest.dto.PublicUserDTO;
import com.qa.choonz.utils.PasswordAuthentication;

import net.bytebuddy.implementation.bytecode.Throw;

@Service
public class AdminUserService {

	private AdminUserRepository repo;
	private ModelMapper mapper;
	private static final PasswordAuthentication passwordAuth = new PasswordAuthentication(10);

	public AdminUserService(AdminUserRepository repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private AdminUserDTO mapToDTO(AdminUser user) {
		return this.mapper.map(user, AdminUserDTO.class);
	}
	

	public AdminUser create(AdminUser user) {
		char[] pass = user.getPassword().toCharArray();
		user.setPassword(passwordAuth.hash(pass));
		System.out.println(user);
		return this.repo.save(user);
	}

	public List<AdminUserDTO> read() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public AdminUserDTO read(Long id) {
		AdminUser found = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		return this.mapToDTO(found);
	}

	public AdminUserDTO update(AdminUser user, Long id) {
		AdminUser toUpdate = this.repo.findById(id).orElseThrow(UserNotFoundException::new);
		toUpdate.setName(user.getName());
		toUpdate.setUsername(user.getUsername());
		PasswordAuthentication hashPass = new PasswordAuthentication();
		char[] pass = user.getPassword().toCharArray();
		toUpdate.setPassword(hashPass.hash(pass));
		
		System.out.println(toUpdate);
		
		AdminUser updated = this.repo.save(toUpdate);
		
		System.out.println(updated);
		
		return this.mapToDTO(updated);

	}

	public boolean delete(Long id) {
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	
}
