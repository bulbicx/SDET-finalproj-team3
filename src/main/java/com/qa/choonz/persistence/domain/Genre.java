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
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(max = 100)
	@Column(unique = true)
	private String name;

	@NotNull
	@Size(max = 250)
	@Column(unique = true)
	private String description;

	@JsonIgnore
	@OneToMany(mappedBy = "genre", cascade = CascadeType.ALL)
	private List<Album> albums;
	
	@OneToOne
    @JoinColumn(name="image_id")
    private Image image;

	public Genre() {
		super();
	}

	public Genre(Long id, @NotNull @Size(max = 100) String name, @NotNull @Size(max = 250) String description,
			List<Album> albums, Image image) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
	public String toString() {
		return "Genre [id=" + id + ", name=" + name + ", description=" + description + ", albums=" + albums + ", image="
				+ image + "]";
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
		Genre other = (Genre) obj;
		return Objects.equals(albums, other.albums) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(image, other.image)
				&& Objects.equals(name, other.name);
	}

}
