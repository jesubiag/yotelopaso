package com.yotelopaso.presenters;


import com.vaadin.data.Item;
import com.vaadin.ui.Link;
import com.yotelopaso.YotelopasoUI;
import com.yotelopaso.domain.File.Type;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.UserManager;
import com.yotelopaso.views.SubjectsView;

public class SubjectsPresenter extends AbstractHomePresenter<SubjectsView> implements SubjectsView.SubjectsViewListener {
	
	private SubjectsView view;
	private UserManager userService;
	private String userCareer;

	public SubjectsPresenter(SubjectsView view, UserManager userService) {
		super(view);
		
		this.view = view;
		this.userService = userService;
		
		view.addListener(this);
	}

	@Override
	public void treeItemClick(String caption) {
		// TODO: Arreglar, esto debería funcionar, no debería repetirse el contenido del método en este
		//super.panelButtonClick(caption);
		
		// Acciones para el tree
		if (caption.contains("Materias")) {
			// TODO: hacer algo en la view
			view.toggleTreeRoot(caption);
			return;
		} else {
			this.userCareer = userService.getCurrentUser().getCareer().getName();
			view.cleanComponents();
			view.navigate(YotelopasoUI.SUBJECTS_VIEW + "/" + caption);
		}
	}
	
	@Override
	public void tableItemClick(Item item) {
		String fileAuthor = (String) item.getItemProperty("Autor").getValue();
		String fileDate = (String) item.getItemProperty("Fecha de creación").getValue();
		String fileDesc = (String) item.getItemProperty("Descripción").getValue();
		Long fileId = (Long) item.getItemProperty("ID").getValue();
		Link l = (Link) item.getItemProperty("Nombre").getValue();
		view.showFileDetail(fileId,fileAuthor, fileDate, l.getCaption(), fileDesc);
	}
	
	@Override
	public void buttonClick(String caption, Long newsId) {
		panelButtonClick(caption);
		
		switch (caption) {
		case "Nueva Noticia":
			view.showNewsEditorWindow(null);
			break;
		case "Editar":
			view.showNewsEditorWindow(newsId);
			break;
		case "Eliminar":
			// TODO: acá hay que delegar. Parte de deleteNew se hace acá y lo demás en la vista
			view.deleteNew(newsId);
			//newsService.delete(newsId);
			break;
		case "Editar ":
			view.showUploadFileWindow(newsId);
			break;
		case "Eliminar ":
			view.deleteFile(newsId);
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

	@Override
	public void setDefaultContent() {
		User currentUser = userService.getCurrentUser();
		String careerName = currentUser.getCareer().getName();
		view.setSubjects(careerName);
	}

	@Override
	public void setSubjectContent(String subjectName) {
		view.cleanComponents();
		String careerName = userService.getCurrentUser().getCareer().getName();
		view.buildSubjectLayout(careerName,subjectName);
	}

	@Override
	public void selectedTabChange(String caption) {
		//String currentPage = StringUtils.parseURL(Page.getCurrent().getUriFragment())[1];
		//System.out.println(caption);
		//Page.getCurrent().setUriFragment(caption);
		//view.nagivate(Vaadintest01UI.SUBJECTS_VIEW + "/" + currentPage + "/" + caption);
		
	}

	
}
