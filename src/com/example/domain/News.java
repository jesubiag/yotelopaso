package com.example.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class News implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	@ManyToOne
	private Career career;
	
	@ManyToOne
	private Subject subject;
	
	private String title;
	
	private String content;
	
	public News() {}
	
	/**
	 * @param date
	 * @param career
	 * @param subject
	 * @param content
	 */
	public News(Date date, Career career, Subject subject, String title,
			String content) {
		this.date = date;
		this.title = title;
		this.career = career;
		this.subject = subject;
		this.content = content;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date fecha) {
		this.date = fecha;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String titulo) {
		this.title = titulo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String contenido) {
		this.content = contenido;
	}
	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}
