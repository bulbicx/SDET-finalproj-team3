package com.qa.choonz.persistence.domain;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Image {
	

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column
    private String name;
	@Column
    private String type;
	// image bytes can have large lengths so we specify a value
	// which is more than the default length for picByte column
	@NotNull
	@Lob
	@Column(name = "picByte", length = 1000)
    private byte[] picByte;
    
    public Image() {
        super();
    }
    public Image(Long id ,String name, String type, byte[] picByte) {
    	this.id = id;
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

	public Image(String name, String type, byte[] picByte) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getType() {
        return type;
    }

	public void setType(String type) {
        this.type = type;
	}

	public byte[] getPicByte() {
        return picByte;
    }

	public void setPicByte(byte[] picByte) {
       this.picByte = picByte;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(picByte);
		result = prime * result + Objects.hash(id, name, type);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Image other = (Image) obj;
		return Objects.equals(id, other.id) && Objects.equals(name, other.name) && Arrays.equals(picByte, other.picByte)
				&& Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", name=" + name + ", type=" + type + ", picByte=" + Arrays.toString(picByte) + "]";
	}
	
	
}
