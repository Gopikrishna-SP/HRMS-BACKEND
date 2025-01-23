package com.kryptos.hrms.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Entity
public class Announcement {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long announcementId;

	private String title;

	private String description;

	private LocalDate datePublished;

	private String author;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;

	public Announcement() {
		// TODO Auto-generated constructor stub
	}

	public Announcement(Long announcementId, String title, String description, LocalDate datePublished,
			String author, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.announcementId = announcementId;
		this.title = title;
		this.description = description;
		this.datePublished = datePublished;
		this.author = author;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}
	
	@PrePersist
	public void onCreate() {
		this.createdAt = LocalDateTime.now();
		this.updatedAt = null;
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updatedAt = LocalDateTime.now();
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
