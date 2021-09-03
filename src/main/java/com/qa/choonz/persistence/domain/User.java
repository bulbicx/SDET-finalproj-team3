package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Inheritance ( strategy = InheritanceType.SINGLE_TABLE ) 
@Table(name="User")
public abstract class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String username;
    
    @NotNull
    @Size(max = 100)
    private String name;
    
    @NotNull
    @Size(max = 100)
    //This breaks integration tests if it's here, but also breaks them in a different way if it's gone :(
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
    
    @JsonIgnore
    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Session> sessions;
    
    public User() {
    	super();
    }

	public User(Long id, @NotNull @Size(max = 100) String username, @NotNull @Size(max = 100) String name,
			@NotNull @Size(max = 100) String password, List<Session> sessions) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.password = password;
		this.sessions = sessions;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", name=" + name + ", password=" + password + ", sessions="
				+ sessions + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, password, sessions, username);
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
		return Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(sessions, other.sessions)
				&& Objects.equals(username, other.username);
	}
	
	
    
    

}
