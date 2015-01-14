package com.yotelopaso.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.vaadin.ui.components.calendar.event.BasicEvent;

@Entity
public class UserCalendarEvent extends BasicEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	private Double authorId;
	private Integer subjectId;
	
	public UserCalendarEvent() {}
	
	public UserCalendarEvent(Double authorId, Integer subjectId, String caption,
			String description, Date start, Date end) {
		this.authorId = authorId;
		this.subjectId = subjectId;
		setCaption(caption);
		setDescription(description);
		setStart(start);
		setEnd(end);
	}
	
	public UserCalendarEvent(Double authorId, Integer subjectId, String caption,
			String description, Date uniqueDate) {
		this.authorId = authorId;
		this.subjectId = subjectId;
		setCaption(caption);
		setDescription(description);
		setStart(uniqueDate);
		setEnd(uniqueDate);
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Double authorId) {
		this.authorId = authorId;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
}
