package com.example.vaadintest01;

import javax.servlet.annotation.WebServlet;

import com.example.persistence.NewsManager;
import com.example.persistence.UserManager;
import com.example.presenters.HomePresenter;
import com.example.presenters.MainPresenter;
import com.example.presenters.SubjectsPresenter;
import com.example.utils.DataInitializer;
import com.example.views.CompDatosView;
import com.example.views.EditorNoticiasView;
import com.example.views.implementations.HomeViewImpl;
import com.example.views.implementations.MainViewImpl;
import com.example.views.implementations.SubjectsViewImpl;
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
		
		// Datos de prueba
		DataInitializer.populateTables();
		
		//getSession().setAttribute("userId", 0D);
		
		nav = new Navigator(this, this);
		
		// Managers
		UserManager userManager = new UserManager();
		NewsManager newsManager = new NewsManager();
		
		// Views
		MainViewImpl mainView = new MainViewImpl();
		HomeViewImpl homeView = new HomeViewImpl();
		SubjectsViewImpl subjectsView = new SubjectsViewImpl();
		
		// Navigator
		nav.addView(MAIN_VIEW, mainView);
		nav.addView(HOME_VIEW, homeView);
		nav.addView(SUBJECTS_VIEW, subjectsView);
		nav.addView(REGISTER_VIEW, new CompDatosView());
		nav.addView(EDITORVIEW, new EditorNoticiasView());
		
		// Presenters
		new MainPresenter(mainView);
		new HomePresenter(homeView, userManager);
		new SubjectsPresenter(subjectsView, userManager, newsManager);
		
		nav.navigateTo(MAIN_VIEW);
	}

}