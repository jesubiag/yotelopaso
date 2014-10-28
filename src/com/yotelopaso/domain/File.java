package com.yotelopaso.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class File implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum Type {
		PARCIAL,
		TP,
		FINAL,
		APUNTE,
	}

	@Id
	@GeneratedValue
	private Long id;
	
	private String url;
	
	@ManyToOne
	private User author;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date creationDate;
	
	@ManyToOne
	private Subject subject;
	
	@Transient
	private Career career;
	
	private Type type;
	
	private String description;
	
	public File() {};

	/**
	 * @param url
	 * @param author
	 * @param name
	 * @param creationDate
	 * @param subject
	 * @param career
	 * @param type
	 * @param description
	 */
	public File(String url, User author, String name, Date creationDate,
			Subject subject, Career career, Type type, String description) {
		this.url = url;
		this.author = author;
		this.name = name;
		this.creationDate = creationDate;
		this.subject = subject;
		this.career = career;
		this.type = type;
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Career getCareer() {
		return this.subject.getCareer();
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
