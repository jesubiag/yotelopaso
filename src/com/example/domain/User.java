package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String lastName;
	
	@NotNull
	private String email;
	
	@NotNull
	private Long idCarreer;
	
	@Min(1) @Max(5)
	private Integer year;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date fechaNacimiento) {
		this.birthday = fechaNacimiento;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String nombre) {
		this.name = nombre;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String apellido) {
		this.lastName = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getIdCarreer() {
		return idCarreer;
	}
	public void setIdCarreer(Long idCarrera) {
		this.idCarreer = idCarrera;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer año) {
		this.year = año;
	}

}
