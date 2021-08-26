package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;

public class TrackBuilder {
	
    private Long id;
    private String name;
    private Album album;
	private List<Playlist> playlists;
    private int duration;
    private String lyrics;

	public TrackBuilder() {
	}

	public Track build() {
		return new Track(id, name, album, playlists, duration, lyrics);
	}
	
	public TrackBuilder id(Long id) {
		this.id = id;
		return this;
	}
	
	public TrackBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public TrackBuilder album(Album album) {
		this.album = album;
		return this;
	}
	
	public TrackBuilder playlists(List<Playlist> playlists) {
		this.playlists = playlists;
		return this;
	}
	
	public TrackBuilder duration(int duration) {
		this.duration = duration;
		return this;
	}
	
	public TrackBuilder lyrics(String lyrics) {
		this.lyrics = lyrics;
		return this;
	}
	
}
