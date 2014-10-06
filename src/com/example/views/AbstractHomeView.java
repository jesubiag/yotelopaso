package com.example.views;

public interface AbstractHomeView {
	
	interface AbstractHomeViewListener {
		void panelButtonClick(String caption);
	}
	
	public void addListener(AbstractHomeViewListener listener);

}
