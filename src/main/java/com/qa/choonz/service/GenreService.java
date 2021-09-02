package com.qa.choonz.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qa.choonz.exception.ArtistNotFoundException;
import com.qa.choonz.exception.GenreNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.builder.AlbumBuilder;
import com.qa.choonz.persistence.domain.builder.GenreBuilder;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.persistence.repository.ImageRepository;
import com.qa.choonz.persistence.repository.SessionRepository;
import com.qa.choonz.rest.dto.AlbumDTO;
import com.qa.choonz.rest.dto.GenreDTO;

@Service
public class GenreService {

    private GenreRepository repo;
    private ImageRepository imageRepo;
    private SessionRepository sessionRepo;
    private ModelMapper mapper;

    @Autowired
    public GenreService(
    		GenreRepository repo, 
    		ModelMapper mapper, 
    		ImageRepository imageRepo,
    		SessionRepository sessionRepo) {
        super();
        this.repo = repo;
        this.imageRepo = imageRepo;
        this.mapper = mapper;
        this.sessionRepo = sessionRepo;
    }

    private GenreDTO mapToDTO(Genre genre) {
        return this.mapper.map(genre, GenreDTO.class);
    }

    public GenreDTO create(
    		MultipartFile file, 
    		String name, 
    		String description,
    		String token) throws Exception {
    	authenticate(token);
    	Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		Image savedImage = this.imageRepo.save(image);
        Genre genre = new GenreBuilder()
        		.name(name)
        		.description(description)
        		.image(savedImage)
        		.build();
        Genre created = this.repo.save(genre);
        return this.mapToDTO(created);
    }

    public List<GenreDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public GenreDTO read(Long id) {
        Genre found = this.repo.findById(id).orElseThrow(GenreNotFoundException::new);
        return this.mapToDTO(found);
    }

    public GenreDTO update(Genre genre, Long id) {
        Genre toUpdate = this.repo.findById(id).orElseThrow(GenreNotFoundException::new);
        toUpdate.setName(genre.getName());
        toUpdate.setDescription(genre.getDescription());
        Genre updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(Long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }

    public void authenticate(String token) throws Exception {
		if(sessionRepo.existsByToken(token)) {
			return;
		} else {
			throw new Exception("Session not found");
		}	
	}
}
