package com.qa.choonz.persistence.domain;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Session {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @ManyToOne
    private User user;
    
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String token;
    
    public Session() {
    	super();
    	
    }
    
	public Session(Long id, @NotNull PublicUser user,
			@NotNull @Size(max = 100) String token) {
		super();
		this.id = id;
		this.user = user;
		this.token = token;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(id, token, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Session other = (Session) obj;
		return Objects.equals(id, other.id) && Objects.equals(token, other.token) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Session [id=" + id + ", user=" + user + ", token=" + token + "]";
	}

	
	
    
    
}
