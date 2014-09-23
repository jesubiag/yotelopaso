package com.example.vaadintest01;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.example.domain.News;
import com.example.persistence.NewsManager;
import com.example.utils.DataInitializer;
import com.example.views.CompDatosView;
import com.example.views.EditorNoticiasView;
import com.example.views.HomeView;
import com.example.views.MainView;
import com.example.views.SubjectsView;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@Theme("vaadintest01")
//@Theme("dawn")
@Push
@PreserveOnRefresh
public class Vaadintest01UI extends UI {
	
	private static final long serialVersionUID = 1L;

	public static final String PERSISTENCE_UNIT = "vaadintest";
	
	// UI Components declaration
	
	//Navigator
	Navigator nav;
	public static final String MAIN_VIEW = "";
	public static final String HOME_VIEW = "home";
	public static final String REGISTER_VIEW = "register";
	public static final String EDITORVIEW = "editornoticias";
	public static final String SUBJECTS_VIEW = "materias";
	
	final VerticalLayout layout = new VerticalLayout();

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadintest01UI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	@Override
	protected void init(VaadinRequest request) {
		
		// datos de prueba
		DataInitializer.populateTables();
		//System.out.println(UserManager.isRegistered(user1.getId() - 456000000D));
		
		//getSession().setAttribute("userId", 0D);
		
		
		nav = new Navigator(this, this);
		nav.addView(MAIN_VIEW, new MainView());
		nav.addView(HOME_VIEW, new HomeView());
		nav.addView(REGISTER_VIEW, new CompDatosView());
		nav.addView(EDITORVIEW, new EditorNoticiasView());
		nav.addView(SUBJECTS_VIEW, new SubjectsView());
		nav.navigateTo(MAIN_VIEW);
		
	}

}