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

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String username;
    
    @NotNull
    @Size(max = 100)
    private String name;
    
    //Make sure this is hashed or in some way protected
    @NotNull
    @Size(max = 100)
    private String password;
    
    //potentially other elements e.g. email, age that don't matter as much
    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Playlist> playlists;
    
    public User() {
    	super();
    }

	public User(long id, @NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String name,
			@NotNull @Size(max = 100) String password, List<Playlist> playlists) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.playlists = playlists;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	
	//doesn't show password, even if it's hashed
	@Override
	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("User [id=").append(id).append(", username=").append(username).append(", name=")
        		.append(name).append(", playlists=").append(playlists).append("]");
        return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, password, playlists, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return id == other.id && Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(playlists, other.playlists) && Objects.equals(username, other.username);
	}
	
	
    
    
}
