package com.yotelopaso.views;

public interface AbstractHomeView extends AuthView {
	
	interface AbstractHomeViewListener {
		void panelButtonClick(String caption);
	}
	
	public void addListener(AbstractHomeViewListener listener);

}
