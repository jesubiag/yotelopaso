package com.example.views;

public interface AbstractHomeView {
	
	interface AbstractHomeViewListener {
		void buttonClick(String caption);
	}
	
	public void addListener(AbstractHomeViewListener listener);

}
