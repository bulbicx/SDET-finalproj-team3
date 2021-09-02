package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Image;


public class ArtistDTO {

    private long id;
    private String name;
    private List<Album> albums;
    private Image image;

    public ArtistDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArtistDTO(long id, String name, List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.albums = albums;
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return Objects.hash(albums, id, image, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArtistDTO other = (ArtistDTO) obj;
		return Objects.equals(albums, other.albums) && id == other.id && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "ArtistDTO [id=" + id + ", name=" + name + ", albums=" + albums + ", image=" + image + "]";
	}

    
    
    

}
