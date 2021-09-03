package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.choonz.persistence.domain.Image;
import com.qa.choonz.persistence.domain.Track;
import com.qa.choonz.persistence.domain.PublicUser;

public class PlaylistDTO {

    private long id;
    private String name;
    private String description;
    private Image artwork;
    private List<Track> tracks;
    private PublicUser user;

    public PlaylistDTO() {
        super();
        // TODO Auto-generated constructor stub
    }



    public PlaylistDTO(long id, String name, String description, Image artwork, List<Track> tracks, PublicUser user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
		this.tracks = tracks;
		this.user = user;
	}



	/**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    

    public Image getArtwork() {
		return artwork;
	}



	public void setArtwork(Image artwork) {
		this.artwork = artwork;
	}



	/**
     * @return the tracks
     */
    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * @param tracks the tracks to set
     */
    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    
    public PublicUser getUser() {
    	return user;
    }
    
    public void setUser(PublicUser user) {
    	this.user=user;
    }

    @Override
	public String toString() {
		return "PlaylistDTO [id=" + id + ", name=" + name + ", description=" + description + ", artwork=" + artwork
				+ ", tracks=" + tracks + ", user=" + user + "]";
	}

    @Override
	public int hashCode() {
		return Objects.hash(artwork, description, id, name, tracks, user);
	}

    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaylistDTO other = (PlaylistDTO) obj;
		return Objects.equals(artwork, other.artwork) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(name, other.name) && Objects.equals(tracks, other.tracks)
				&& Objects.equals(user, other.user);
	}

}
