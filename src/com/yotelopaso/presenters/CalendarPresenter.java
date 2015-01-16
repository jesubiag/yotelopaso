package com.yotelopaso.presenters;

import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.CalendarView;

public class CalendarPresenter extends AbstractHomePresenter<CalendarView> 
implements CalendarView.CalendarViewListener {

	public CalendarPresenter(CalendarView view, UserManager service) {
		super(view, service);
		// TODO Auto-generated constructor stub
	}

}
