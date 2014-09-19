package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Temporal(TemporalType.DATE)
	private Date date;
	
	private Long idCareer;
	
	private String subject;
	
	private String content;
	
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
	public Long getIdCarreer() {
		return idCareer;
	}
	public void setIdCarreer(Long carrera) {
		this.idCareer = carrera;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String materia) {
		this.subject = materia;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String contenido) {
		this.content = contenido;
	}

}
