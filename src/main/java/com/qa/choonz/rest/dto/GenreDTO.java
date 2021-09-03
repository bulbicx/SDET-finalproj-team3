package com.qa.choonz.rest.dto;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qa.choonz.persistence.domain.Album;
import com.qa.choonz.persistence.domain.Image;

public class GenreDTO {

    private Long id;
    private String name;
    private String description;
    private List<Album> albums;
    private Image image;

    public GenreDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    public GenreDTO(Long id, String name, String description, List<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.description = description;
        this.albums = albums;
    }

    public long getId() {
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
		return Objects.hash(albums, description, id, image, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenreDTO other = (GenreDTO) obj;
		return Objects.equals(albums, other.albums) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "GenreDTO [id=" + id + ", name=" + name + ", description=" + description + ", albums=" + albums
				+ ", image=" + image + "]";
	}

	

}
