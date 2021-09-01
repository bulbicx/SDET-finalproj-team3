package com.qa.choonz.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.rest.dto.SessionDTO;
import com.qa.choonz.rest.dto.UserDTO;
import com.qa.choonz.service.SessionService;
import com.qa.choonz.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
	
	private UserService service;
	private SessionService sessionService;

	public UserController(UserService service, SessionService sessionService) {
		super();
		this.service = service;
		this.sessionService = sessionService;
	}
	
    @PostMapping("/create")
    public ResponseEntity<SessionDTO> create(@RequestBody User user) {
    	User createdUser = this.service.create(user);
        return new ResponseEntity<SessionDTO>(this.sessionService.createSession(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<UserDTO>> read() {
        return new ResponseEntity<List<UserDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<UserDTO> read(@PathVariable Long id) {
        return new ResponseEntity<UserDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> update(@RequestBody User user, @PathVariable long id) {
        return new ResponseEntity<UserDTO>(this.service.update(user, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<UserDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<UserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

}
