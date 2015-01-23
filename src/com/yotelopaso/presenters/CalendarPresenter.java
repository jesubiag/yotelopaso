package com.yotelopaso.presenters;

import com.yotelopaso.persistence.UserCalendarEventManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.CalendarView;

public class CalendarPresenter extends AbstractHomePresenter<CalendarView> 
implements CalendarView.CalendarViewListener {
	
	CalendarView view;
	UserCalendarEventManager service;

	public CalendarPresenter(CalendarView view, UserManager userService,
			UserCalendarEventManager service) {
		super(view, userService);
		this.view = view;
		this.service = service;
		
		view.addListener(this);
	}
	
	@Override
	public void buttonClick(String caption) {
		super.panelButtonClick(caption);
		// otras decisiones acá
	}

}
