package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.choonz.persistence.domain.Playlist;


public abstract class UserDTO {
	
	private Long id;
	private String username;
	private String name;
	
	public UserDTO() {
		super();
	}

	public UserDTO(Long id, String username, String name) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserDTO [id=").append(id).append(", username=").append(username).append(", name=")
        		.append(name).append("]");
        return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDTO other = (UserDTO) obj;
		return id == other.id && Objects.equals(name, other.name)
				&& Objects.equals(username, other.username);
	}
	
}
