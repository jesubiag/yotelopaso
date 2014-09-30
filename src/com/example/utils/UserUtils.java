package com.example.utils;

import com.vaadin.server.VaadinSession;

public class UserUtils {
	
	public static boolean isLogged(Double userId) {
		if (userId == (Double) 0D || userId == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static void logOff(VaadinSession session) {
		// tal vez sea mas conveniente setear el atributo userId a 0D
		session.close();
	}
}