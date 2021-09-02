package com.qa.choonz.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Session;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.PublicUser;
import com.qa.choonz.persistence.domain.builder.ArtistBuilder;
import com.qa.choonz.persistence.domain.builder.PlaylistBuilder;
import com.qa.choonz.persistence.repository.ImageRepository;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.SessionRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.persistence.repository.PublicUserRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;

@Service
public class PlaylistService {

	private PlaylistRepository playlistRepo;
	private PublicUserRepository userRepo;
	private TrackRepository trackRepo;
	private ImageRepository imageRepo;
	private SessionRepository sessionRepo;
	private ModelMapper mapper;


	public PlaylistService(
			PlaylistRepository playlistRepo, 
			TrackRepository trackRepo, 
			ModelMapper mapper,
			PublicUserRepository userRepo,
			ImageRepository imageRepo,
			SessionRepository sessionRepo) {
		super();
		this.playlistRepo = playlistRepo;
		this.trackRepo = trackRepo;
		this.userRepo = userRepo;
		this.imageRepo = imageRepo;
		this.sessionRepo = sessionRepo;
		this.mapper = mapper;
	}

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	public PlaylistDTO create(String token, MultipartFile file, String name, String description) throws IOException {
		Session session = this.sessionRepo.findByToken(token);
		Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		Image savedImage = this.imageRepo.save(image);
		PublicUser user = (PublicUser) session.getUser();
        Playlist playlist = new PlaylistBuilder()
        		.name(name)
        		.description(description)
        		.user(user)
        		.artwork(savedImage)
        		.build();
        Playlist created = this.playlistRepo.save(playlist);
        return this.mapToDTO(created);
	}

	public List<PlaylistDTO> read() {
		return this.playlistRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public PlaylistDTO read(Long id) {
		Playlist found = this.playlistRepo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		return this.mapToDTO(found);
	}

	public PlaylistDTO update(Playlist playlist, Long id) {
		Playlist toUpdate = this.playlistRepo.findById(id).orElseThrow(PlaylistNotFoundException::new);
		toUpdate.setName(playlist.getName());
		toUpdate.setDescription(playlist.getDescription());
		toUpdate.setArtwork(playlist.getArtwork());
		Playlist updated = this.playlistRepo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	public PlaylistDTO addTrack(Long playlistId, Long trackId) {
		Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
		Playlist playlist = this.playlistRepo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);
		List<Track> tracks = playlist.getTracks();
		tracks.add(track);
		playlist.setTracks(tracks);
		Playlist trackAdded = this.playlistRepo.save(playlist);
		return this.mapToDTO(trackAdded);
	}

	public PlaylistDTO removeTrack(Long playlistId, Long trackId) {
		Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
		Playlist playlist = this.playlistRepo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);
		List<Track> tracks = playlist.getTracks();

		if (tracks.contains(track)) {
			tracks.remove(track);
		}

		playlist.setTracks(tracks);

		Playlist trackRemoved = this.playlistRepo.save(playlist);
		return this.mapToDTO(trackRemoved);
	}

	public boolean delete(Long id) {
		this.playlistRepo.deleteById(id);
		return !this.playlistRepo.existsById(id);
	}

}
