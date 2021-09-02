package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.User;

public class UserDomainTest {
	
	private User user = new User(0L, "username", "real name", "password", new ArrayList<>());
	
	@Test
	public void UserTest() {
		assertThat("User [id=0, username=username, name=real name, password=password, playlists=[], sessions=null]").isEqualTo(user.toString());
		
	}

}
