package com.qa.choonz.unittest.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.repository.PublicUserRepository;
import com.qa.choonz.rest.dto.PublicUserDTO;
import com.qa.choonz.service.PublicUserService;

@SpringBootTest
public class UserServiceTest {
	
	@MockBean
	private PublicUserRepository repo;
	
	@Autowired
	private PublicUserService service;
	
	private PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>(), new ArrayList<>());
	private PublicUserDTO userDTO = new PublicUserDTO(0L, "username", "real name",new ArrayList<>());
	private Optional<PublicUser> optionalUser = Optional.of(new PublicUser(0L, "username", "real name", "password", new ArrayList<>(), new ArrayList<>()));
	private PublicUser newUser = new PublicUser(0L, "username2", "real name2","password123", new ArrayList<>(), new ArrayList<>());
	private PublicUserDTO newUserDTO = new PublicUserDTO(0L, "username2", "real name2", new ArrayList<>());
	
	@Test
	public void UserCreateTest() {
		
		Mockito.when(this.repo.save(user)).thenReturn(user);
		
		assertThat(user).isEqualTo(this.service.create(user));
		
		Mockito.verify(this.repo, Mockito.times(1)).save(user);
	}
	
	@Test
	public void UserReadAllTest() {
		
		Mockito.when(this.repo.findAll()).thenReturn(new ArrayList<>());
		
		assertThat(new ArrayList<>()).isEqualTo(this.service.read());
		
		Mockito.verify(this.repo, Mockito.times(1)).findAll();
	}
	
	@Test
	public void UserReadByIdTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalUser);
		
		assertThat(userDTO).isEqualTo(this.service.read(0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
	}
	
	@Test
	public void UserUpdateTest() {
		Mockito.when(this.repo.findById(0L)).thenReturn(optionalUser);
		Mockito.when(this.repo.save(newUser)).thenReturn(newUser);
		
		assertThat(newUserDTO).isEqualTo(this.service.update(newUser, 0L));
		
		Mockito.verify(this.repo, Mockito.times(1)).findById(0L);
		Mockito.verify(this.repo, Mockito.times(1)).save(newUser);
	}
	
	@Test
	public void UserDeleteTest() {
		assertThat(true).isEqualTo(this.service.delete(0L));
	}

}
