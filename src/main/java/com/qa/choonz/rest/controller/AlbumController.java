package com.qa.choonz.rest.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.service.AlbumService;

@RestController
@RequestMapping("/albums")
@CrossOrigin
public class AlbumController {

    private AlbumService service;

    public AlbumController(AlbumService service) {
        super();
        this.service = service;
    }

    @PostMapping("/create/artist/{artistId}/genre/{genreId}")
    public ResponseEntity<AlbumDTO> create(
    		@PathVariable Long artistId,
    		@PathVariable Long genreId, 
    		@RequestParam("file") MultipartFile file,
    		@RequestParam("name") String name,
    		@RequestParam("token") String token)  throws Exception {
        return new ResponseEntity<AlbumDTO>(this.service.create(artistId, genreId, file, name, token), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<AlbumDTO>> read() {
        return new ResponseEntity<List<AlbumDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<AlbumDTO> read(@PathVariable Long id) {
        return new ResponseEntity<AlbumDTO>(this.service.read(id), HttpStatus.OK);
    }
    
    @PutMapping("/{albumId}/addTrack/{trackId}")
	public ResponseEntity<AlbumDTO> addTrack(@PathVariable Long albumId, @PathVariable Long trackId) {
		return new ResponseEntity<AlbumDTO>(this.service.addTrack(albumId, trackId), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{albumId}/removeTrack/{trackId}")
	public ResponseEntity<AlbumDTO> removeTrack(@PathVariable Long albumId, @PathVariable Long trackId) {
		return new ResponseEntity<AlbumDTO>(this.service.removeTrack(albumId, trackId), HttpStatus.ACCEPTED);
	}

    @PutMapping("/update/{id}/{artistId}/{genreId}")
    public ResponseEntity<AlbumDTO> update(@RequestBody Album album, @PathVariable Long id, @PathVariable (value="artistId") Long artistId,
    		@PathVariable (value="genreId") Long genreId) {
        return new ResponseEntity<AlbumDTO>(this.service.update(album, id, artistId, genreId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AlbumDTO> delete(@PathVariable Long id) {
        return this.service.delete(id) ? new ResponseEntity<AlbumDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<AlbumDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
