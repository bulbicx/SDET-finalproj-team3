package com.qa.choonz.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.qa.choonz.exception.TrackNotFoundException;
import com.qa.choonz.exception.AlbumNotFoundException;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.repository.AlbumRepository;
import com.qa.choonz.persistence.repository.TrackRepository;
import com.qa.choonz.rest.dto.TrackDTO;

@Service
public class TrackService {

    private TrackRepository trackRepo;
    private AlbumRepository albumRepo;
    private ModelMapper mapper;

    public TrackService(TrackRepository trackRepo, AlbumRepository albumRepo, ModelMapper mapper) {
        super();
        this.trackRepo = trackRepo;
        this.albumRepo = albumRepo;
        this.mapper = mapper;
    }

    private TrackDTO mapToDTO(Track track) {
        return this.mapper.map(track, TrackDTO.class);
    }

    public TrackDTO create(Track track, Long albumId) {
    	Album album = this.albumRepo.findById(albumId).orElseThrow(AlbumNotFoundException::new);
    	track.setAlbum(album);
        Track created = this.trackRepo.saveAndFlush(track);
        return this.mapToDTO(created);
    }

    public List<TrackDTO> read() {
        return this.trackRepo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public TrackDTO read(Long id) {
        Track found = this.trackRepo.findById(id).orElseThrow(TrackNotFoundException::new);
        return this.mapToDTO(found);
    }

    public TrackDTO update(Track track, Long id, Long albumId) {
        Track toUpdate = this.trackRepo.findById(id).orElseThrow(TrackNotFoundException::new);
        Album albumToUpdate = this.albumRepo.findById(albumId).orElseThrow(AlbumNotFoundException::new);
        toUpdate.setName(track.getName());
        toUpdate.setAlbum(albumToUpdate);
        toUpdate.setDuration(track.getDuration());
        toUpdate.setLyrics(track.getLyrics());
        Track updated = this.trackRepo.save(toUpdate);
        return this.mapToDTO(updated);
    }

    public boolean delete(Long id) {
        this.trackRepo.deleteById(id);
        return !this.trackRepo.existsById(id);
    }
}
