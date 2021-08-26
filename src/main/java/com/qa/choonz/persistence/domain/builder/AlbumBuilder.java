package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Track;

public class AlbumBuilder {
    private Long id;
    private String name;
    private List<Track> tracks;
    private Artist artist;
    private Genre genre;
    private String cover;


	public AlbumBuilder() {
	}

	public Album build() {
		return new Album(id, name, tracks, artist, genre, cover);
	}

	public AlbumBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public AlbumBuilder name(String name) {
		this.name = name;
		return this;
	}

	public AlbumBuilder tracks(List<Track> tracks) {
		this.tracks = tracks;
		return this;
	}

	public AlbumBuilder artist(Artist artist) {
		this.artist = artist;
		return this;
	}
	
	public AlbumBuilder genre(Genre genre) {
		this.genre = genre;
		return this;
	}
	
	public AlbumBuilder cover(String cover) {
		this.cover = cover;
		return this;
	}
	

}
