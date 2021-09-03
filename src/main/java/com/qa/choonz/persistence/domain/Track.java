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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String name;
    
    @JsonIgnore
    @ManyToOne
    private Album album;

    @JsonIgnore
    @ManyToMany(fetch=FetchType.LAZY,
    			cascade= {CascadeType.PERSIST, CascadeType.MERGE,
    					CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
    		name = "playlist_track",
    		joinColumns = @JoinColumn(name = "track_id"),
    		inverseJoinColumns = @JoinColumn(name = "playlist_id")
    )
	private List<Playlist> playlists;

    // in seconds
    private int duration;

    private String lyrics;

    public Track() {
        super();
    }

    public Track(Long id, @NotNull @Size(max = 100) String name, Album album, List<Playlist> playlists, int duration,
            String lyrics) {
        super();
        this.id = id;
        this.name = name;
        this.album = album;
        this.playlists = playlists;
        this.duration = duration;
        this.lyrics = lyrics;
    }
    
    public Track(Long id, @NotNull @Size(max = 100) String name, Album album, int duration, String lyrics) {
        super();
        this.id = id;
        this.name = name;
        this.album = album;
        this.duration = duration;
        this.lyrics = lyrics;
    }
    
    public Track(@NotNull @Size(max = 100) String name, Album album, int duration, String lyrics) {
        super();
        this.name = name;
        this.album = album;
        this.duration = duration;
        this.lyrics = lyrics;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Playlist> getPlaylist() {
        return playlists;
    }

    public void setPlaylist(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Track [id=").append(id).append(", name=").append(name).append(", album=").append(album)
                .append(", playlist=").append(playlists).append(", duration=").append(duration).append(", lyrics=")
                .append(lyrics).append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(album, duration, id, lyrics, name, playlists);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }
        Track other = (Track) obj;
        return Objects.equals(album, other.album) && duration == other.duration && id == other.id
                && Objects.equals(lyrics, other.lyrics) && Objects.equals(name, other.name)
                && Objects.equals(playlists, other.playlists);
    }

}
