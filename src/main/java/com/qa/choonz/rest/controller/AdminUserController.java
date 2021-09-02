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

import com.qa.choonz.persistence.domain.AdminUser;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.rest.dto.SessionDTO;
import com.qa.choonz.rest.dto.AdminUserDTO;
import com.qa.choonz.rest.dto.PublicUserDTO;
import com.qa.choonz.service.SessionService;
import com.qa.choonz.service.AdminUserService;
import com.qa.choonz.service.PublicUserService;

@RestController
@RequestMapping("/users/admin")
@CrossOrigin
public class AdminUserController {
	
	private AdminUserService service;
	private SessionService sessionService;

	public AdminUserController(AdminUserService service, SessionService sessionService) {
		super();
		this.service = service;
		this.sessionService = sessionService;
	}
	
    @PostMapping("/create")
    public ResponseEntity<SessionDTO> create(@RequestBody AdminUser user) {
    	AdminUser createdUser = this.service.create(user);
        return new ResponseEntity<SessionDTO>(this.sessionService.createSession(createdUser), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<AdminUserDTO>> read() {
        return new ResponseEntity<List<AdminUserDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<AdminUserDTO> read(@PathVariable Long id) {
        return new ResponseEntity<AdminUserDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AdminUserDTO> update(@RequestBody AdminUser user, @PathVariable long id) {
        return new ResponseEntity<AdminUserDTO>(this.service.update(user, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AdminUserDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<AdminUserDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<AdminUserDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

}
