package com.qa.choonz.unittest.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.UserService;

@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private UserRepository repo;
	
	@Autowired
	private UserService service;
	
	private User user = new User(0, "username", "real name", "password", new ArrayList<>());
	private UserDTO userDTO = new UserDTO(0, "username", "real name", new ArrayList<>());
	private Optional<User> optionalUser = Optional.of(new User(0, "username", "real name", "password", new ArrayList<>()));
	private User newUser = new User(0, "username2", "real name2", "password123", new ArrayList<>());
	private UserDTO newUserDTO = new UserDTO(0, "username2", "real name2", new ArrayList<>());
	
	@Test
	public void UserCreateTest() {
		
		Mockito.when(this.repo.save(user)).thenReturn(user);
		
		assertEquals(userDTO,this.service.create(user));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(user);
	}
	
	@Test
	public void UserReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertEquals(new ArrayList<>(), this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void UserReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalUser);
		
		assertEquals(userDTO, this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void UserUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalUser);
		Mockito.when(this.repo.save(newUser)).thenReturn(newUser);
		
		assertEquals(newUserDTO, this.service.update(newUser, 0));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newUser);
	}
	
	@Test
	public void UserDeleteTest() {
		assertEquals(true,this.service.delete(0L));
	}

}
