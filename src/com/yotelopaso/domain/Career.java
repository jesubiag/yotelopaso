package com.yotelopaso.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Career implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	@NotNull
	private String name;
	
	public Career() {}
	
	/**
	 * @param id
	 * @param name
	 */
	public Career(Integer id, String name) {
		this.id = id;
		this.name = name;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
