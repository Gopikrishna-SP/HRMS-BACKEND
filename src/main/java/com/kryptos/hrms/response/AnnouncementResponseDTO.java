package com.kryptos.hrms.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnnouncementResponseDTO {

	private Long announcementId;

	private String title;

	private String description;

	private LocalDate datePublished;

	private String author;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public AnnouncementResponseDTO() {
		// TODO Auto-generated constructor stub
	}

	public AnnouncementResponseDTO(Long announcementId, String title, String description,
			LocalDate datePublished, String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.announcementId = announcementId;
		this.title = title;
		this.description = description;
		this.datePublished = datePublished;
		this.author = author;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
