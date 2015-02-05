package com.yotelopaso.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import com.vaadin.ui.components.calendar.event.CalendarEvent.EventChangeNotifier;
import com.vaadin.ui.components.calendar.event.EditableCalendarEvent;
import com.yotelopaso.persistence.constraints.NotEmpty;

@Entity
public class UserCalendarEvent implements EditableCalendarEvent, EventChangeNotifier {
	
	public enum CalendarEventType {
		
		ENTREGA("Entrega", "color1"),
		FINAL("Final", "color2"),
		PARCIAL("Parcial", "color3"),
		PRESENTACION("Presentación", "color4"),
		OTRO("Otro", "color5"),
		NONE(null , null);
		
		private String name;
		private String color;
		
		private CalendarEventType(String name, String color) {
			this.name = name;
			this.color = color;
		}
		
		@Override
		public String toString() {
			return name;
		}
		
		public String getName() {
			return name;
		}
		
		public String getColor() {
			return color;
		}
		
		public static List<CalendarEventType> getAll() {
			List<CalendarEventType> l = new ArrayList<CalendarEventType>();
			l.add(ENTREGA);
			l.add(FINAL);
			l.add(PARCIAL);
			l.add(PRESENTACION);
			l.add(OTRO);
			return l;
		}
	}
	
	private static final long serialVersionUID = 1353312615888351945L;
	@Id
	@GeneratedValue
	private Long id;
	private Double authorId;
	@Min(1)
	private Integer subjectId;
	@ManyToOne
	private Career career;
	private String caption;
	private String description;
	@Temporal(TemporalType.TIMESTAMP)
	private Date start;
	@Temporal(TemporalType.TIMESTAMP)
	private Date end;
	private String styleName;
	private boolean isAllDay;
	@ManyToMany(mappedBy="userEvents")
	private Set<User> suscriptors = new HashSet<User>();
	@Enumerated(EnumType.ORDINAL) @NotEmpty
	private CalendarEventType eventType;
	private boolean publicEvent = false;
	@Transient
	private List<EventChangeListener> listeners = new ArrayList<EventChangeListener>();
	
	public UserCalendarEvent() {}
	
	public UserCalendarEvent(Double authorId, Integer subjectId, String caption,
			String description, Date start, Date end) {
		this.authorId = authorId;
		this.subjectId = subjectId;
		this.caption = caption;
		this.description = description;
		this.start = start;
		this.end = end;
	}
	
	public UserCalendarEvent(Double authorId, Integer subjectId, String caption,
			String description, Date date) {
		this.authorId = authorId;
		this.subjectId = subjectId;
		this.caption = caption;
		this.description = description;
		start = date;
		end = date;
	}

	public Double getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Double authorId) {
		this.authorId = authorId;
		fireEventChange();
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
		fireEventChange();
	}

	public Set<User> getSuscriptors() {
		return suscriptors;
	}

	public void setSuscriptors(Set<User> suscriptors) {
		this.suscriptors = suscriptors;
		fireEventChange();
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getCaption() {
		return caption;
	}

	@Override
	public void setCaption(String caption) {
		this.caption = caption;
		fireEventChange();
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
		fireEventChange();
	}

	@Override
	public Date getStart() {
		return start;
	}

	@Override
	public void setStart(Date start) {
		this.start = start;
		fireEventChange();
	}

	@Override
	public Date getEnd() {
		return end;
	}
	
	@Override
	public void setEnd(Date end) {
		this.end = end;
		fireEventChange();
	}

	@Override
	public String getStyleName() {
		return styleName;
	}

	@Override
	public void setStyleName(String styleName) {
		this.styleName = styleName;
		fireEventChange();
	}

	@Override
	public boolean isAllDay() {
		return isAllDay;
	}

	@Override
	public void setAllDay(boolean isAllDay) {
		this.isAllDay = isAllDay;
		fireEventChange();
	}
	
	@Override
	public void addEventChangeListener(EventChangeListener listener) {
		listeners.add(listener);
	}
	
	@Override
	public void removeEventChangeListener(EventChangeListener listener) {
		listeners.remove(listener);
	}
	
	protected void fireEventChange() {
		EventChangeEvent event = new EventChangeEvent(this);
		for (EventChangeListener listener : listeners) {
			listener.eventChange(event);
		}
	}
	
	public void addSuscriptor(User suscriptor) {
		suscriptors.add(suscriptor);
		fireEventChange();
	}
	
	public void removeSuscriptor(User suscriptor) {
		suscriptors.remove(suscriptor);
		fireEventChange();
	}

	public CalendarEventType getEventType() {
		return eventType;
	}

	public void setEventType(CalendarEventType eventType) {
		this.eventType = eventType;
	}

	public Career getCareer() {
		return career;
	}

	public void setCareer(Career career) {
		this.career = career;
	}

	public boolean getPublicEvent() {
		return publicEvent;
	}

	public void setPublicEvent(boolean publicEvent) {
		this.publicEvent = publicEvent;
	}
}
