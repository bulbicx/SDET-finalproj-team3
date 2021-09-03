package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Genre;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.PublicUser;

public class GenreBuilder {
	private Long id;
	private String name;
	private String description;
	private Image image;
	private List<Album> albums;

	public GenreBuilder() {
	}

	public Genre build() {
		return new Genre(id, name, description, albums, image);
	}

	public GenreBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public GenreBuilder name(String name) {
		this.name = name;
		return this;
	}

	public GenreBuilder description(String description) {
		this.description = description;
		return this;
	}

	public GenreBuilder image(Image image) {
		this.image = image;
		return this;
	}

	public GenreBuilder albums(List<Album> albums) {
		this.albums = albums;
		return this;
	}
	
	

}
