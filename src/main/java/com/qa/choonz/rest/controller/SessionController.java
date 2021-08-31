package com.qa.choonz.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.SessionDTO;
import com.qa.choonz.service.SessionService;

@RestController
@RequestMapping("/sessions")
@CrossOrigin
public class SessionController {
	
	private SessionService service;

	public SessionController(SessionService service) {
		super();
		this.service = service;
	}
	
	 @PostMapping("/authenticate")
	 public ResponseEntity<SessionDTO> authenticate(@RequestBody User user){
	    	return  new ResponseEntity<SessionDTO>(this.service.authenticate(user), HttpStatus.OK);
	 }

}
