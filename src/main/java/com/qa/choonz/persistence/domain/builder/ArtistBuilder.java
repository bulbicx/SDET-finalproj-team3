package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Artist;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.User;

public class ArtistBuilder {
	private Long id;
	private String name;
	private Image image;
	private List<Album> albums;

	public ArtistBuilder() {
	}

	public Artist build() {
		return new Artist(id, name, albums, image);
	}

	public ArtistBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public ArtistBuilder name(String name) {
		this.name = name;
		return this;
	}

	public ArtistBuilder image(Image image) {
		this.image = image;
		return this;
	}

	public ArtistBuilder albums(List<Album> albums) {
		this.albums = albums;
		return this;
	}

}
