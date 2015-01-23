package com.yotelopaso.presenters;


import com.vaadin.data.Item;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Link;
import com.yotelopaso.Vaadintest01UI;
import com.yotelopaso.domain.File.Type;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.persistence.NewsManager;
import com.yotelopaso.persistence.SubjectManager;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.SubjectsView;

public class SubjectsPresenter extends AbstractHomePresenter<SubjectsView> implements SubjectsView.SubjectsViewListener {
	
	private SubjectsView view;
	private NewsManager newsService;
	private SubjectManager subjectService;
	private UserManager userService;
	private String userCareer;
	private FileManager fileService;

	public SubjectsPresenter(SubjectsView view, UserManager userService, 
			SubjectManager subjectService, FileManager fileService, NewsManager newsService) {
		super(view, userService);
		
		this.view = view;
		this.subjectService = subjectService;
		this.userService = userService;
		this.fileService = fileService;
		this.newsService = newsService;
		
		view.addListener(this);
	}

	@Override
	public void treeItemClick(String caption) {
		// TODO: Arreglar, esto debería funcionar, no debería repetirse el contenido del método en este
		//super.panelButtonClick(caption);
		
		// Acciones para el tree
		if (caption.contains("Materias")) {
			// hacer algo en la view
			view.toggleTreeRoot(caption);
			return;
		} else {
			this.userCareer = userService.getCurrentUser().getCareer().getName();
			view.cleanComponents();
			view.navigate(Vaadintest01UI.SUBJECTS_VIEW + "/" + caption);
		}
	}
	
	@Override
	public void tableItemClick(Item item) {
		String fileAuthor = (String) item.getItemProperty("Autor").getValue();
		String fileDate = (String) item.getItemProperty("Fecha de creación").getValue();
		String fileDesc = (String) item.getItemProperty("Descripción").getValue();
		Link l = (Link) item.getItemProperty("Nombre").getValue();
		view.showFileDetail(fileAuthor, fileDate, l.getCaption(), fileDesc);
	}
	
	@Override
	public void buttonClick(String caption) {
		
		switch (caption) {
		case "Inicio":
			view.navigate(Vaadintest01UI.HOME_VIEW);
			break;
		case "Materias":
			view.navigate(Vaadintest01UI.SUBJECTS_VIEW);
			break;
		case "Mi Calendario":
			view.navigate(Vaadintest01UI.CALENDAR_VIEW);
			break;
		case "Mi Actividad":
			//navigator.navigateTo(Vaadintest01UI.ACTIVITY_VIEW);
			break;
		case "Logout":
			VaadinSession.getCurrent().setAttribute("userId", null);
			VaadinSession.getCurrent().setAttribute("currentUser", null);
			view.navigate(Vaadintest01UI.MAIN_VIEW);
			break;
		default:
			//view.buildSubjectLayout(caption);
			break;
		}
	}

	@Override
	public void setDefaultContent() {
		User currentUser = userService.getCurrentUser();
		String careerName = currentUser.getCareer().getName();
		view.setSubjects(careerName);
	}

	@Override
	public void setSubjectContent(String subjectName) {
		view.cleanComponents();
		view.buildSubjectLayout(subjectName, userCareer);
	}

	@Override
	public void selectedTabChange(String caption) {
		//String currentPage = StringUtils.parseURL(Page.getCurrent().getUriFragment())[1];
		//System.out.println(caption);
		//Page.getCurrent().setUriFragment(caption);
		//view.nagivate(Vaadintest01UI.SUBJECTS_VIEW + "/" + currentPage + "/" + caption);
		
	}

	@Override
	public void buttonClick(String caption,Long newsId) {
		// TODO: pasar el evento a la vista, que solo reciba la data del componente
		switch (caption) {
		case "Nueva Noticia":
			view.showNewsEditorWindow(null);
			break;
		case "Editar":
			view.showNewsEditorWindow(newsId);
			break;
		case "Eliminar":
			view.deleteNew((Long)newsId);
			//newsService.delete(newsId);
			break;
		case "Subir Parciales":
			view.showUploadFileWindow(Type.PARCIAL);
			break;
		case "Subir Finales":
			view.showUploadFileWindow(Type.FINAL);
			break;
		case "Subir TPs":
			view.showUploadFileWindow(Type.TP);
			break;
		case "Subir Apuntes":
			view.showUploadFileWindow(Type.APUNTE);
			break;
		}
	}
}
