package com.qa.choonz.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.rest.dto.PlaylistDTO;
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
	
	 @PostMapping("/authenticate/public")
	 public ResponseEntity<SessionDTO> authenticate(@RequestBody PublicUser user){
	    	return  new ResponseEntity<SessionDTO>(this.service.authenticate(user), HttpStatus.OK);
	 }
	 
	 @PostMapping("/authenticate/admin")
	 public ResponseEntity<SessionDTO> authenticate(@RequestBody AdminUser user){
	    	return  new ResponseEntity<SessionDTO>(this.service.authenticate(user), HttpStatus.OK);
	 }
	 
	 @DeleteMapping("/delete/{token}")
	 public ResponseEntity<SessionDTO> delete(@PathVariable String token) {
			return this.service.delete(token) ? new ResponseEntity<SessionDTO>(HttpStatus.NO_CONTENT)
					: new ResponseEntity<SessionDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	 }

}
