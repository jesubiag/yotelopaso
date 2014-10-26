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

}
