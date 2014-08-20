package com.example.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
	@OneToOne
	private Career career;
	
	@Min(1) @Max(5)
	private Integer year;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@OneToOne
	PersonalInfo personalinfo;
	

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
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer año) {
		this.year = año;
	}
	public PersonalInfo getPersonalinfo() {
		return personalinfo;
	}
	public void setPersonalinfo(PersonalInfo personalinfo) {
		this.personalinfo = personalinfo;
	}
	public Career getCareer() {
		return career;
	}
	public void setCareer(Career career) {
		this.career = career;
	}

}
