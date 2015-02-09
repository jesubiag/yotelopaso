package com.yotelopaso.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.yotelopaso.persistence.constraints.OnlyLettersOrDash;

@Entity
public class PersonalInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idUsuario;
	
	@OnlyLettersOrDash
	private String city;
	
	private Long phone;
	
	private String avatar;
	
	public PersonalInfo() {}

	/**
	 * @param idUsuario
	 * @param city
	 * @param phone
	 * @param avatar
	 */
	public PersonalInfo(Long idUsuario, String city, Long phone, String avatar) {
		this.idUsuario = idUsuario;
		this.city = city;
		this.phone = phone;
		this.avatar = avatar;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

}
