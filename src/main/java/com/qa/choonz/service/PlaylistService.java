package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.PlaylistNotFoundException;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.exception.UserNotFoundException;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;
import com.qa.choonz.persistence.repository.PlaylistRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.persistence.repository.UserRepository;
import com.qa.choonz.rest.dto.PlaylistDTO;

@Service
public class PlaylistService {

	private PlaylistRepository playlistRepo;
	private TrackRepository trackRepo;
	private UserRepository userRepo;
	private ModelMapper mapper;

	public PlaylistService(PlaylistRepository playlistRepo, TrackRepository trackRepo, UserRepository userRepo, ModelMapper mapper) {
		super();
		this.playlistRepo = playlistRepo;
		this.trackRepo = trackRepo;
		this.userRepo = userRepo;
		this.mapper = mapper;
	}

	private PlaylistDTO mapToDTO(Playlist playlist) {
		return this.mapper.map(playlist, PlaylistDTO.class);
	}

	public PlaylistDTO create(Playlist playlist, Long userId) {
		User userFound = this.userRepo.findById(userId).orElseThrow(UserNotFoundException::new);
		playlist.setUser(userFound);
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
		toUpdate.setTracks(playlist.getTracks());
		Playlist updated = this.playlistRepo.save(toUpdate);
		return this.mapToDTO(updated);
	}

	public PlaylistDTO addTrack(Long playlistId, Long trackId) {
		Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
		Playlist playlist = this.playlistRepo.findById(playlistId).orElseThrow(PlaylistNotFoundException::new);
		List<Track> tracks = playlist.getTracks();
//    	List<Playlist> playlistTracks = track.getPlaylist();

		tracks.add(track);
//    	playlistTracks.add(playlist);
		playlist.setTracks(tracks);
//    	track.setPlaylist(playlistTracks);

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
