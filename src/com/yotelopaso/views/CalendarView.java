package com.yotelopaso.views;

public interface CalendarView extends AbstractHomeView {
	
	interface CalendarViewListener extends AbstractHomeViewListener {
		public void buttonClick(String caption);
	}
	
	public void addListener(CalendarViewListener listener);

}
