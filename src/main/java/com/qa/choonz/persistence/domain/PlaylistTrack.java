package com.qa.choonz.persistence.domain;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlaylistTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Track track;

	@ManyToOne
	private Playlist playlist;

	public PlaylistTrack() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaylistTrack(long id, Track track, Playlist playlist) {
		super();
		this.id = id;
		this.track = track;
		this.playlist = playlist;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Track getTrack() {
		return track;
	}

	public void setTrack(Track track) {
		this.track = track;
	}

	public Playlist getPlaylist() {
		return playlist;
	}

	public void setPlaylist(Playlist playlist) {
		this.playlist = playlist;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, playlist, track);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaylistTrack other = (PlaylistTrack) obj;
		return id == other.id && Objects.equals(playlist, other.playlist) && Objects.equals(track, other.track);
	}

}
