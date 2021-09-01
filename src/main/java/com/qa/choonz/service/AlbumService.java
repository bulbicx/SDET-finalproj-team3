package com.qa.choonz.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.jdbc.datasource.embedded.ConnectionProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.exception.ArtistNotFoundException;
import com.qa.choonz.exception.GenreNotFoundException;
import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.builder.AlbumBuilder;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.ArtistRepository;
import com.qa.choonz.persistence.repository.GenreRepository;
import com.qa.choonz.persistence.repository.ImageRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.AlbumDTO;

@Service
public class AlbumService {

	private AlbumRepository albumRepo;
	private ArtistRepository artistRepo;
	private GenreRepository genreRepo;
	private TrackRepository trackRepo;
	private ImageRepository imageRepo;
	private ModelMapper mapper;

	public AlbumService(AlbumRepository albumRepo, ModelMapper mapper, ArtistRepository artistRepo,
			GenreRepository genreRepo, TrackRepository trackRepo, ImageRepository imageRepo) {
		super();
		this.albumRepo = albumRepo;
		this.artistRepo = artistRepo;
		this.genreRepo = genreRepo;
		this.trackRepo = trackRepo;
		this.imageRepo = imageRepo;
		this.mapper = mapper;
	}

	private AlbumDTO mapToDTO(Album album) {
		return this.mapper.map(album, AlbumDTO.class);
	}

	public AlbumDTO create(Long artistId, Long genreId, MultipartFile file, String name) throws IOException {
		Album album = new AlbumBuilder().name(name).build();
		Optional<Artist> artistOpt = this.artistRepo.findById(artistId);
		artistOpt.orElseThrow(() -> new ArtistNotFoundException());
		Optional<Genre> genreOpt = this.genreRepo.findById(genreId);
		genreOpt.orElseThrow(() -> new GenreNotFoundException());
		album.setArtist(artistOpt.get());
		album.setGenre(genreOpt.get());
		Image image = new Image(file.getOriginalFilename(), file.getContentType(), file.getBytes());
		Image savedImage = this.imageRepo.save(image);
		album.setCover(savedImage);
		Album created = this.albumRepo.save(album);
		return this.mapToDTO(created);
	}

	public List<AlbumDTO> read() {
		return this.albumRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public AlbumDTO read(long id) {
		Album found = this.albumRepo.findById(id).orElseThrow(AlbumNotFoundException::new);
		return this.mapToDTO(found);
	}

	public AlbumDTO update(Album album, long id, Long artistId, Long genreId) {
		Album toUpdate = this.albumRepo.findById(id).orElseThrow(AlbumNotFoundException::new);
		Artist artistToUpdate = this.artistRepo.findById(artistId).orElseThrow(ArtistNotFoundException::new);
		Genre genreToUpdate = this.genreRepo.findById(genreId).orElseThrow(GenreNotFoundException::new);
		toUpdate.setName(album.getName());
		toUpdate.setTracks(album.getTracks());
		toUpdate.setArtist(artistToUpdate);
		toUpdate.setGenre(genreToUpdate);
//		toUpdate.setCover(album.getCover());
		Album updated = this.albumRepo.save(toUpdate);
		return this.mapToDTO(updated);
	}
	
	public AlbumDTO addTrack(Long albumId, Long trackId) {
		Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
		Album album = this.albumRepo.findById(albumId).orElseThrow(AlbumNotFoundException::new);
		List<Track> tracks = album.getTracks();
		tracks.add(track);
		album.setTracks(tracks);
		Album trackAdded = this.albumRepo.save(album);
		return this.mapToDTO(trackAdded);
	}

	public AlbumDTO removeTrack(Long albumId, Long trackId) {
		Track track = this.trackRepo.findById(trackId).orElseThrow(TrackNotFoundException::new);
		Album album = this.albumRepo.findById(albumId).orElseThrow(AlbumNotFoundException::new);
		List<Track> tracks = album.getTracks();

		if (tracks.contains(track)) {
			tracks.remove(track);
		}

		album.setTracks(tracks);

		Album trackRemoved = this.albumRepo.save(album);
		return this.mapToDTO(trackRemoved);
	}

	public boolean delete(long id) {
		this.albumRepo.deleteById(id);
		return !this.albumRepo.existsById(id);
	}

}
