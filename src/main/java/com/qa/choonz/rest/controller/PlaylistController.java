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

import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.PlaylistDTO;
import com.qa.choonz.service.PlaylistService;

@RestController
@RequestMapping("/playlists")
@CrossOrigin
public class PlaylistController {

	private PlaylistService service;

	public PlaylistController(PlaylistService service) {
		super();
		this.service = service;
	}


	@PostMapping("/create/user/{sessionId}")
	public ResponseEntity<PlaylistDTO> create(
			@RequestParam("file") MultipartFile file,
    		@RequestParam("name") String name,
    		@RequestParam("description") String description,
    		@RequestParam("token") String token) throws IOException{

		return new ResponseEntity<PlaylistDTO>(this.service.create(token, file, name, description), HttpStatus.CREATED);
	}

	@GetMapping("/read")
	public ResponseEntity<List<PlaylistDTO>> read() {
		return new ResponseEntity<List<PlaylistDTO>>(this.service.read(), HttpStatus.OK);
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<PlaylistDTO> read(@PathVariable Long id) {
		return new ResponseEntity<PlaylistDTO>(this.service.read(id), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PlaylistDTO> update(@RequestBody Playlist playlist, @PathVariable Long id) {
		return new ResponseEntity<PlaylistDTO>(this.service.update(playlist, id), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{playlistId}/addTrack/{trackId}")
	public ResponseEntity<PlaylistDTO> addTrack(@PathVariable Long playlistId, @PathVariable Long trackId) {
		return new ResponseEntity<PlaylistDTO>(this.service.addTrack(playlistId, trackId), HttpStatus.ACCEPTED);
	}

	@PutMapping("/{playlistId}/removeTrack/{trackId}")
	public ResponseEntity<PlaylistDTO> removeTrack(@PathVariable Long playlistId, @PathVariable Long trackId) {
		return new ResponseEntity<PlaylistDTO>(this.service.removeTrack(playlistId, trackId), HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<PlaylistDTO> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<PlaylistDTO>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<PlaylistDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
