package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Session {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@ManyToOne
	private Long idUser;
	
	@Temporal(TemporalType.DATE)
	private Date in;
	
	@Temporal(TemporalType.DATE)
	private Date out;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser= idUser;
	}

	public Date getIn() {
		return in;
	}

	public void setIn(Date in) {
		this.in = in;
	}

	public Date getOut() {
		return out;
	}

	public void setOut(Date out) {
		this.out = out;
	}

}
