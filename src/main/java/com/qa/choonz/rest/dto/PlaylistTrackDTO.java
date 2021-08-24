package com.qa.choonz.rest.dto;

import java.util.Objects;

public class PlaylistTrackDTO {

	private long id;
	private TrackDTO track;
	private PlaylistDTO playlist;

	public PlaylistTrackDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaylistTrackDTO(long id, TrackDTO track, PlaylistDTO playlist) {
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

	public TrackDTO getTrack() {
		return track;
	}

	public void setTrack(TrackDTO track) {
		this.track = track;
	}

	public PlaylistDTO getPlaylist() {
		return playlist;
	}

	public void setPlaylist(PlaylistDTO playlist) {
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
		PlaylistTrackDTO other = (PlaylistTrackDTO) obj;
		return id == other.id && Objects.equals(playlist, other.playlist) && Objects.equals(track, other.track);
	}

}
