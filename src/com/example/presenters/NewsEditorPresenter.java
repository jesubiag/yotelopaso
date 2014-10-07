package com.example.presenters;

import com.example.domain.Career;
import com.example.domain.News;
import com.example.domain.Subject;
import com.example.persistence.NewsManager;
import com.example.persistence.SubjectManager;
import com.example.views.components.NewsEditor;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

public class NewsEditorPresenter implements NewsEditor.NewsEditorListener {
	
	NewsEditor view;
	NewsManager manager;
	SubjectManager matManager;
	News noticia;
	
	
	public NewsEditorPresenter(NewsEditor view, NewsManager service, SubjectManager matManager) {
		this.view = view;
		this.manager = new NewsManager();
		this.matManager = new SubjectManager();
		
		view.addListener(this);
	}

	@Override
	public void buttonClick(String caption) {
		switch (caption) {
		case "Aceptar":
			noticia = new News();
			ComboBox mat = view.getMat();
			TextField titulo = view.getTit();
			TextField contenido = view.getCont();
			DateField fecha = view.getFecha();
			
			Career carrera = new Career();
			carrera.setName("Ingeniería en Sistemas");
			
			Subject materia = new Subject();
			matManager.getById((Integer) mat.getValue());
			
			if (mat.isValid() & titulo.isValid()) {
				noticia.setCareer(carrera);
				noticia.setContent(contenido.getValue());
				noticia.setDate(fecha.getValue());
				noticia.setSubject((Subject) materia);
				noticia.setTitle(titulo.getValue());
				manager.save(noticia);
			}
			else {	
				Notification.show("Revise los campos obligatorios",
		                Notification.Type.WARNING_MESSAGE);	
			}
			break;
		case "Cancelar":
			break;
		}
	}

}
