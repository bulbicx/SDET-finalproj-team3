package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.choonz.persistence.domain.Playlist;


public class PublicUserDTO extends UserDTO{
	
	private List<Playlist> playlists;
	
	public PublicUserDTO() {
		super();
	}

	public PublicUserDTO(Long id, String username, String name, List<Playlist> playlists) {
		super(id, username, name);
		this.playlists = playlists;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(playlists);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PublicUserDTO other = (PublicUserDTO) obj;
		return Objects.equals(playlists, other.playlists);
	}

	@Override
	public String toString() {
		return "PublicUserDTO [playlists=" + playlists + ", getId()=" + getId() + ", getUsername()=" + getUsername()
				+ ", getName()=" + getName() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}

	

	
	
	


	
	
	
}
