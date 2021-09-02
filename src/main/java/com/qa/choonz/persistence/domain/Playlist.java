package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;

    @NotNull
    @Size(max = 500)
    @Column(unique = true)
    private String description;

    @OneToOne
    @JoinColumn(name="image_id")
    private Image artwork;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY,
    			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
    					CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
    		name = "playlist_track",
    		joinColumns = @JoinColumn(name = "playlist_id"),
    		inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private List<Track> tracks;
    
    @JsonIgnore
    @ManyToOne
    private User user;

    public Playlist() {
		super();
	}

	public Playlist(Long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 500) String description,
			Image artwork, List<Track> tracks, User user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.artwork = artwork;
		this.tracks = tracks;
		this.user = user;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getArtwork() {
		return artwork;
	}

	public void setArtwork(Image artwork) {
		this.artwork = artwork;
	}

	public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
	public String toString() {
		return "Playlist [id=" + id + ", name=" + name + ", description=" + description + ", artwork=" + artwork
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
		Playlist other = (Playlist) obj;
		return Objects.equals(artwork, other.artwork) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name)
				&& Objects.equals(tracks, other.tracks) && Objects.equals(user, other.user);
	}

}
