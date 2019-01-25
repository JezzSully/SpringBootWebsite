package com.sullivan.jeremy.databaseapp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "film_list")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"last_update"}, allowGetters = true)
public class FilmModel implements Serializable
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long FID;
	
	private String title;
	
	private String description;
	
	private String category;
	
	private String price;
	
	private String length;
	
	private String rating;
	
	public Long getFID() {
		return FID;
	}

	public void setFID(Long fID) {
		FID = fID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	private String actors;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
