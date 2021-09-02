package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.choonz.persistence.domain.Playlist;


public class AdminUserDTO extends UserDTO{

	public AdminUserDTO() {
		super();
	}

	public AdminUserDTO(Long id, String username, String name) {
		super(id, username, name);
	}

	
	
	

}
