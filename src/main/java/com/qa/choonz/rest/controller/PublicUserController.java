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

import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.rest.dto.SessionDTO;
import com.qa.choonz.rest.dto.PublicUserDTO;
import com.qa.choonz.service.SessionService;
import com.qa.choonz.service.PublicUserService;

@RestController
@RequestMapping("/users/public")
@CrossOrigin
public class PublicUserController {
	
	private PublicUserService service;
	private SessionService sessionService;

	public PublicUserController(PublicUserService service, SessionService sessionService) {
		super();
		this.service = service;
		this.sessionService = sessionService;
	}
	
    @PostMapping("/create")
    public ResponseEntity<SessionDTO> create(@RequestBody PublicUser user) {
    	PublicUser createdUser = this.service.create(user);
        return new ResponseEntity<SessionDTO>(this.sessionService.createSession(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<PublicUserDTO>> read() {
        return new ResponseEntity<List<PublicUserDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<PublicUserDTO> read(@PathVariable Long id) {
        return new ResponseEntity<PublicUserDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PublicUserDTO> update(@RequestBody PublicUser user, @PathVariable long id) {
        return new ResponseEntity<PublicUserDTO>(this.service.update(user, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PublicUserDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<PublicUserDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<PublicUserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

}
