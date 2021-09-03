package com.qa.choonz.persistence.domain.builder;

import java.util.List;

import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Playlist;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.PublicUser;

public class PlaylistBuilder {
	private Long id;
	private String name;
	private String description;
	private Image artwork;
	private List<Track> tracks;
	private PublicUser user;

	public PlaylistBuilder() {
	}

	public Playlist build() {
		return new Playlist(id, name, description, artwork, tracks, user);
	}

	public PlaylistBuilder id(Long id) {
		this.id = id;
		return this;
	}

	public PlaylistBuilder name(String name) {
		this.name = name;
		return this;
	}

	public PlaylistBuilder description(String description) {
		this.description = description;
		return this;
	}

	public PlaylistBuilder artwork(Image artwork) {
		this.artwork = artwork;
		return this;
	}

	public PlaylistBuilder tracks(List<Track> tracks) {
		this.tracks = tracks;
		return this;
	}
	
	public PlaylistBuilder user(PublicUser user) {
		this.user = user;
		return this;
	}
	

}
