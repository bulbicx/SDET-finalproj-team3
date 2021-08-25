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

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
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

    @PostMapping("/create/{artistId}/{genreId}")
    public ResponseEntity<AlbumDTO> create(@RequestBody Album album, @PathVariable (value="artistId") Long artistId,
    		@PathVariable (value="genreId") Long genreId) {
        return new ResponseEntity<AlbumDTO>(this.service.create(album, artistId, genreId), HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<AlbumDTO>> read() {
        return new ResponseEntity<List<AlbumDTO>>(this.service.read(), HttpStatus.OK);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<AlbumDTO> read(@PathVariable long id) {
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
    public ResponseEntity<AlbumDTO> update(@RequestBody Album album, @PathVariable long id, @PathVariable (value="artistId") Long artistId,
    		@PathVariable (value="genreId") Long genreId) {
        return new ResponseEntity<AlbumDTO>(this.service.update(album, id, artistId, genreId), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<AlbumDTO> delete(@PathVariable long id) {
        return this.service.delete(id) ? new ResponseEntity<AlbumDTO>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<AlbumDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
