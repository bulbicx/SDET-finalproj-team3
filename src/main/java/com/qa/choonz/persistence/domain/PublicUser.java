package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class PublicUser extends User {
    
    //Added JsonIgnore here, if stuff breaks this might be why
    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Playlist> playlists;
    
    public PublicUser() {
    	super();
    }


	public PublicUser(Long id, @NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String name,
			@NotNull @Size(max = 100) String password, List<Session> sessions, List<Playlist> playlists) {
		super(id, username, name, password, sessions);
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
		PublicUser other = (PublicUser) obj;
		return Objects.equals(playlists, other.playlists);
	}


	@Override
	public String toString() {
		return "PublicUser [playlists=" + playlists + "]";
	}  
}
