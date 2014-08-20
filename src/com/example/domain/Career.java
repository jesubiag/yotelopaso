package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Career {
	
	//Poblar tabla con los valores acorde a cada id y nombre
	
	@Id
	private Long id;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
