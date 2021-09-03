package com.qa.choonz.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qa.choonz.exception.ArtistNotFoundException;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.domain.builder.ArtistBuilder;
import com.qa.choonz.persistence.domain.builder.GenreBuilder;
import com.qa.choonz.persistence.repository.AdminUserRepository;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.persistence.repository.ImageRepository;
import com.qa.choonz.persistence.repository.SessionRepository;
import com.qa.choonz.rest.dto.ArtistDTO;
import com.qa.choonz.rest.dto.GenreDTO;

@Service
public class ArtistService {

    private ArtistRepository repo;
    private ImageRepository imageRepo;
    private SessionRepository sessionRepo;
    private ModelMapper mapper;
    private AdminUserRepository adminUserRepo;

    public ArtistService(
    		ArtistRepository repo, 
    		ModelMapper mapper, 
    		ImageRepository imageRepo,
    		SessionRepository sessionRepo,
    		AdminUserRepository adminUserRepo) {
        super();
        this.repo = repo;
        this.mapper = mapper;
        this.imageRepo = imageRepo;
        this.sessionRepo = sessionRepo;
        this.adminUserRepo = adminUserRepo;
    }

    private ArtistDTO mapToDTO(Artist artist) {
        return this.mapper.map(artist, ArtistDTO.class);
    }

    public ArtistDTO create(MultipartFile file, String name, String token) throws Exception {
    	authenticateAdmin(token);
    	Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		Image savedImage = this.imageRepo.save(image);
        Artist artist = new ArtistBuilder()
        		.name(name)
        		.image(savedImage)
        		.build();
        Artist created = this.repo.save(artist);
        return this.mapToDTO(created);
    }

    public List<ArtistDTO> read() {
        return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public ArtistDTO read(long id) {
        Artist found = this.repo.findById(id).orElseThrow(ArtistNotFoundException::new);
        return this.mapToDTO(found);
    }

    public ArtistDTO update(Artist artist, long id) {
        Artist toUpdate = this.repo.findById(id).orElseThrow(ArtistNotFoundException::new);
        toUpdate.setName(artist.getName());
        toUpdate.setAlbums(artist.getAlbums());
        Artist updated = this.repo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(long id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
    
    public void authenticateAdmin(String token) throws Exception {
		Session session = sessionRepo.findByToken(token);
		if(session == null) {
			throw new Exception("Session not found");
		}
		User user = session.getUser();
		if(adminUserRepo.existsById(user.getId())) {
			return;
		} else {
			throw new Exception("User no longer exists");
		}	
	}
}
