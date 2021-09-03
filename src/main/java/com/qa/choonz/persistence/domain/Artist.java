package com.qa.choonz.persistence.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
	private List<Album> albums;
	
	@OneToOne
    @JoinColumn(name="image_id")
    private Image image;

	public Artist() {
		super();
	}

	public Artist(Long id, @NotNull @Size(max = 100) String name, List<Album> albums, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.albums = albums;
		this.image = image;
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
		return Objects.hash(albums, image, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(albums, other.albums) && Objects.equals(image, other.image)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Artist [id=" + id + ", name=" + name + ", albums=" + albums + ", image=" + image + "]";
	}

	
}
