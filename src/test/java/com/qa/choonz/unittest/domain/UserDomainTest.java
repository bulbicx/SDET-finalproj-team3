package com.qa.choonz.unittest.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.qa.choonz.persistence.domain.PublicUser;

public class UserDomainTest {
	
	private PublicUser user = new PublicUser(0L, "username", "real name", "password", new ArrayList<>(), new ArrayList<>());
	
	@Test
	public void UserTest() {
		assertThat("PublicUser [playlists=[]]").isEqualTo(user.toString());
		
	}

}
