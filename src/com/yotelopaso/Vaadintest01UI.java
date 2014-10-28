package com.yotelopaso;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.yotelopaso.persistence.CareerManager;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.presenters.DatosRegPresenter;
import com.yotelopaso.presenters.HomePresenter;
import com.yotelopaso.presenters.MainPresenter;
import com.yotelopaso.presenters.SubjectsPresenter;
import com.yotelopaso.utils.DataInitializer;
import com.yotelopaso.views.EditorNoticiasView;
import com.yotelopaso.views.implementations.DatosRegImpl;
import com.yotelopaso.views.implementations.HomeViewImpl;
import com.yotelopaso.views.implementations.MainViewImpl;
import com.yotelopaso.views.implementations.SubjectsViewImpl;

@Theme("vaadintest01")
@Push
@PreserveOnRefresh
public class Vaadintest01UI extends UI {
	
	private static final long serialVersionUID = 1L;

	public static final String PERSISTENCE_UNIT = "vaadintest";
	
	//Navigator
	protected Navigator nav;
	
	// Nombres de las vistas de la aplicación
	public static final String MAIN_VIEW = "";
	public static final String HOME_VIEW = "home";
	public static final String REGISTER_VIEW = "register";
	public static final String EDITORVIEW = "editornoticias";
	public static final String SUBJECTS_VIEW = "materias";
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = Vaadintest01UI.class)
	public static class Servlet extends VaadinServlet {
		private static final long serialVersionUID = 1L;
	}

	@Override
	protected void init(VaadinRequest request) {
		
		// Datos de prueba
		//DataInitializer.populateTables();
		
		//getSession().setAttribute("userId", 0D);
		
		nav = new Navigator(this, this);
		
		// Managers
		UserManager userManager = new UserManager();
		NewsManager newsManager = new NewsManager();
		CareerManager carrManager = new CareerManager();
		SubjectManager subjectManager = new SubjectManager();
		FileManager fileManager = new FileManager();
		
		// Vistas
		MainViewImpl mainView = new MainViewImpl();
		HomeViewImpl homeView = new HomeViewImpl();
		SubjectsViewImpl subjectsView = new SubjectsViewImpl();
		DatosRegImpl datosView = new DatosRegImpl();
		
		// Agrego las vistas al navigator
		nav.addView(MAIN_VIEW, mainView);
		nav.addView(HOME_VIEW, homeView);
		nav.addView(SUBJECTS_VIEW, subjectsView);
		nav.addView(REGISTER_VIEW, datosView);
		nav.addView(EDITORVIEW, new EditorNoticiasView());
		
		// Presenters con sus vistas y managers asociados
		new MainPresenter(mainView);
		new HomePresenter(homeView, userManager);
		SubjectsPresenter subjectsPresenter = new SubjectsPresenter(subjectsView, 
				userManager, subjectManager, fileManager, newsManager);
		new DatosRegPresenter(datosView,userManager,carrManager);
		
		subjectsView.setPresenter(subjectsPresenter);
		
		// Navego a la vista principal
		nav.navigateTo(MAIN_VIEW);
	}

}