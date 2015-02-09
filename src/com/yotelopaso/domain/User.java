package com.yotelopaso.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.yotelopaso.persistence.constraints.OnlyLetters;

@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	private Double id;
	
	@OnlyLetters
	private String name;
	
	@OnlyLetters
	private String lastName;
	
	@NotNull
	private String email;
	
	//@NotNull
	@OneToOne
	private Career career;
	
	@Min(1) @Max(5)
	private Integer year;
	
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@OneToOne
	private PersonalInfo personalinfo;
	
	@ManyToMany
	private Set<UserCalendarEvent> userEvents = new HashSet<UserCalendarEvent>();
	
	@ManyToMany
	private Set<Subject> subscriptedSubjects = new HashSet<Subject>();
	
	public User() {}

	/**
	 * @param id
	 * @param name
	 * @param lastName
	 * @param email
	 * @param career
	 * @param year
	 * @param birthday
	 * @param personalinfo
	 */
	public User(Double id, String name, String lastName, String email,
			Career career, Integer year, Date birthday,
			PersonalInfo personalinfo) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.career = career;
		this.year = year;
		this.birthday = birthday;
		this.personalinfo = personalinfo;
	}

	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date fechaNacimiento) {
		this.birthday = fechaNacimiento;
	}
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
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
	
	@Override
	public String toString() {
		return this.getUsername();
	}
	
	public String getUsername() {
		String userName = this.email.split("@")[0];
		return userName;
	}

	public Set<Subject> getSubscriptedSubjects() {
		return subscriptedSubjects;
	}

	public void setSubscriptedSubjects(Set<Subject> subscriptedSubjects) {
		this.subscriptedSubjects = subscriptedSubjects;
	}
	
	public void addSubjects(Set<Subject> subjects) {
		subscriptedSubjects.addAll(subjects);
	}
	
	public void addSubject(Subject subject) {
		subscriptedSubjects.add(subject);
	}
	
	public void removeSubjects(Set<Subject> subjects) {
		subscriptedSubjects.removeAll(subjects);
	}
}
