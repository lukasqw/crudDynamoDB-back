package com.crud.dynamodb.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class BookDto {
	
	@NotBlank(message = "O campo título deve ser preenchido.")
	String title;
	
	@NotBlank(message = "O campo autor deve ser preenchido.")
    String author;
	
	@NotEmpty(message = "O campo gênero deve ser preenchido.")
    String genre[];
	
	@NotBlank(message = "O campo editora deve ser preenchido.")
    String publisher;
	
	private String synopsis;
	
	private String comments;
	
	private int stars;
	
	private String coverUrl;

	public BookDto() {
	}

	public BookDto(@NotBlank(message = "O campo título deve ser preenchido.") String title,
			@NotBlank(message = "O campo autor deve ser preenchido.") String author,
			@NotEmpty(message = "O campo gênero deve ser preenchido.") String[] genre,
			@NotBlank(message = "O campo editora deve ser preenchido.") String publisher, String synopsis,
			String comments, int stars, String coverUrl) {
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.publisher = publisher;
		this.synopsis = synopsis;
		this.comments = comments;
		this.stars = stars;
		this.coverUrl = coverUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String[] getGenre() {
		return genre;
	}

	public void setGenre(String[] genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
    
	
}
