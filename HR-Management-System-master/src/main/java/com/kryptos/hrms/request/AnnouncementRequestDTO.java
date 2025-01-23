package com.kryptos.hrms.request;

import java.time.LocalDate;

public class AnnouncementRequestDTO {

	private String title;

	private String description;

	private LocalDate datePublished;

	private String author;

	public AnnouncementRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public AnnouncementRequestDTO( String title, String description, LocalDate datePublished,
			String author) {
		super();
		this.title = title;
		this.description = description;
		this.datePublished = datePublished;
		this.author = author;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(LocalDate datePublished) {
		this.datePublished = datePublished;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

}
