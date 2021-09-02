package com.qa.choonz.rest.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.GenreDTO;
import com.qa.choonz.service.GenreService;

@RestController
@RequestMapping("/genres")
@CrossOrigin
public class GenreController {

    private GenreService service;

    public GenreController(GenreService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> create( 
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("name") String name, 
    		@RequestParam("description") String description,
    		@RequestParam("token") String token
    		) throws Exception {
        return new ResponseEntity<GenreDTO>(this.service.create(file, name, description, token), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<GenreDTO>> read() {
        return new ResponseEntity<List<GenreDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<GenreDTO> read(@PathVariable Long id) {
        return new ResponseEntity<GenreDTO>(this.service.read(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenreDTO> update(@RequestBody Genre genre, @PathVariable Long id) {
        return new ResponseEntity<GenreDTO>(this.service.update(genre, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenreDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<GenreDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<GenreDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
