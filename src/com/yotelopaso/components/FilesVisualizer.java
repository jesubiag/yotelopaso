package com.yotelopaso.components;

import java.util.Date;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.domain.File;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.domain.User;
import com.yotelopaso.persistence.FileManager;
import com.yotelopaso.utils.DateUtils;

public class FilesVisualizer extends Window {
	
	private static final long serialVersionUID = 1L;
	private FileManager filemanager = new FileManager();
	private File file = new File();
	private Subject materia;
	private User autor;
	private String url;
	private String fileName;
	private Date fecha;
	private Long id;
	private HorizontalLayout topHorizontalLayout;
	
	public FilesVisualizer(Long id) {
		this.id = id;
		file = filemanager.getById(this.id);
		materia = file.getSubject();
		autor = file.getAuthor();
		fecha = file.getCreationDate();
		url = file.getUrl();
		fileName= file.getName();
		
		
		Label date = new Label();
		date.setValue("Publicado: " + DateUtils.dateFormat(fecha));
		date.setStyleName(ValoTheme.LABEL_TINY);
		date.setSizeUndefined();
		
		Label authorName = new Label();
		authorName.setValue("Autor: " + autor.getUsername());
		authorName.setStyleName(ValoTheme.LABEL_TINY);
		authorName.setSizeUndefined();
		
		Label descripcion = new Label();
		descripcion.setContentMode(ContentMode.HTML);
		descripcion.setValue(file.getDescription());
		descripcion.setWidth("100%");
			
		Label nombre = new Label();
		nombre.setContentMode(ContentMode.HTML);
		nombre.setValue("<b>Archivo:</b> " + "<a href="+"'" + url+ "' "+"target='_blank'>" + fileName + "</a>");
		nombre.setWidth("100%");
		
		Panel panel = new Panel();
		panel.setContent(descripcion);
		panel.setWidth("100%");
		panel.setHeight("300px");
		panel.setStyleName(ValoTheme.PANEL_WELL);
		
		topHorizontalLayout = new HorizontalLayout();
		topHorizontalLayout.setSizeFull();
		topHorizontalLayout.setSpacing(true);
		
		topHorizontalLayout.addComponents(authorName,date);
		topHorizontalLayout.setComponentAlignment(date, Alignment.MIDDLE_RIGHT);
		topHorizontalLayout.setComponentAlignment(authorName, Alignment.MIDDLE_LEFT);
		
		VerticalLayout vLayout = new VerticalLayout();
		vLayout.setMargin(true);
		vLayout.addComponent(nombre);
		vLayout.addComponent(panel);
		vLayout.addComponent(topHorizontalLayout);
		
		setContent(vLayout);
		center();
		setWidth("650px");	
		setResizable(false);
		setModal(true);
		setCaption(materia.getName() + " - " + file.getType().toString());
		
		
		
	}

}
