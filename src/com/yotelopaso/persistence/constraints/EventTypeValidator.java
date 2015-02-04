package com.yotelopaso.persistence.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.yotelopaso.domain.UserCalendarEvent.CalendarEventType;

public class EventTypeValidator implements ConstraintValidator<NotEmpty, CalendarEventType> {

	@Override
	public void initialize(NotEmpty constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(CalendarEventType value, ConstraintValidatorContext context) {
		
		if (value == null)
			return true;
		
		if ( value.getName() == null )
			return false;
		else
			return true;
	}

}
