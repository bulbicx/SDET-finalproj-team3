package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.Session;

public class AdminUserBuilder {

	private Long id;
	private String username; 
	private String name;
	private String password; 
	private List<Session> sessions;
	private List<Playlist> playlists;
	
	public AdminUserBuilder() {
	}

	public AdminUser build() {
		return new AdminUser(id, username, name, password, sessions);
	}

	public AdminUserBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public AdminUserBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public AdminUserBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public AdminUserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public AdminUserBuilder sessions(List<Session> sessions) {
		this.sessions = sessions;
		return this;
	}
	
}

