package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.Session;

public class PublicUserBuilder {

	private Long id;
	private String username; 
	private String name;
	private String password; 
	private List<Session> sessions;
	private List<Playlist> playlists;
	
	public PublicUserBuilder() {
	}

	public PublicUser build() {
		return new PublicUser(id, username, name, password, sessions, playlists);
	}

	public PublicUserBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public PublicUserBuilder username(String username) {
		this.username = username;
		return this;
	}
	
	public PublicUserBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public PublicUserBuilder password(String password) {
		this.password = password;
		return this;
	}
	
	public PublicUserBuilder sessions(List<Session> sessions) {
		this.sessions = sessions;
		return this;
	}
	
	public PublicUserBuilder playlists(List<Playlist> playlists) {
		this.playlists = playlists;
		return this;
	}
}

