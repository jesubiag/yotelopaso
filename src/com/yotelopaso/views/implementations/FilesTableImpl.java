package com.yotelopaso.views.implementations;

import java.util.Date;

import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.presenters.FilesTablePresenter;
import com.yotelopaso.utils.DateUtils;
import com.yotelopaso.views.components.FilesTable;

public class FilesTableImpl extends CustomComponent implements FilesTable {

	private static final long serialVersionUID = 1L;
	
	final private HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
	final private Table filesTable = new Table();
	
	final private VerticalLayout mainLayout = new VerticalLayout();
	final private VerticalLayout fileDetailLayout = new VerticalLayout();

	private FilesTablePresenter presenter;
	private String subjectName;
	private String careerName;
	private String type;
	private ItemClickListener parentView;
	final private Label fileAuthorTitle = new Label("<b>Autor:</b>", ContentMode.HTML);
	final private Label fileDateTitle = new Label("<b>Fecha:</b>", ContentMode.HTML);;
	final private Label fileNameTitle = new Label("<b>Nombre:</b>", ContentMode.HTML);;
	final private Label fileDescTitle = new Label("<b>Descripción:</b>", ContentMode.HTML);;
	final private Label fileAuthor = new Label("", ContentMode.HTML);
	final private Label fileDate = new Label("", ContentMode.HTML);;
	final private Label fileName = new Label("", ContentMode.HTML);;
	final private Label fileDesc = new Label("", ContentMode.HTML);;
	
	public FilesTableImpl(String subjectName, ItemClickListener parentView, 
			String type, String careerName) {
		this.subjectName = subjectName;
		this.careerName = careerName;
		this.type = type;
		this.presenter = new FilesTablePresenter(this);
		this.parentView = parentView;
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}
	
	private void buildMainLayout() {
		
		hsplit.setSizeFull();
		
		//Seteo los elementos de la tabla
		filesTable.setSizeFull();
		filesTable.addStyleName("components-inside");
		String[] tableIds = {"Nombre", "Autor", "Fecha de creación", "Descripción"};
		filesTable.addContainerProperty(tableIds[0], Link.class, null);
		filesTable.addContainerProperty(tableIds[1], String.class, null);
		filesTable.addContainerProperty(tableIds[2], String.class, null);
		filesTable.addContainerProperty(tableIds[3], String.class, null);
		
		filesTable.addStyleName(ValoTheme.TABLE_BORDERLESS);
		filesTable.addStyleName(ValoTheme.TABLE_COMPACT);
		filesTable.addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
		filesTable.addStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);
		filesTable.setColumnReorderingAllowed(true);
		filesTable.setColumnCollapsingAllowed(true);
		filesTable.setColumnCollapsed(tableIds[3], true);
		//TODO: cuando seteo esta propiedad, el contenido deja de mostrarse
		//filesTable.setVisibleColumns(new Object[] {tableIds[0], tableIds[1], tableIds[2]});
		fileDetailLayout.setSizeFull();
		
		fileDesc.setSizeFull();
		
		fileDetailLayout.addComponents(fileNameTitle, fileName);
		fileDetailLayout.addComponents(fileDateTitle, fileDate);
		fileDetailLayout.addComponents(fileAuthorTitle, fileAuthor);
		fileDetailLayout.addComponents(fileDescTitle, fileDesc);
		
		presenter.getData(subjectName, careerName, type);
		
		filesTable.addItemClickListener(parentView);
		filesTable.setData(type);
		
		hsplit.setSplitPosition(23, true);
		hsplit.setLocked(true);
		hsplit.setFirstComponent(filesTable);
		hsplit.setSecondComponent(fileDetailLayout);
		
		mainLayout.setSizeFull();
		mainLayout.addComponent(hsplit);
	}

	@Override
	public void buildTable(String name, String url, String authorName, 
			Date fileDate, Long id, String desc) {
		Link fileLink = new Link(name, new ExternalResource(url));
		fileLink.setTargetName("_blank");
		filesTable.addItem(new Object[] {fileLink, authorName, 
				DateUtils.dateFormat(fileDate), desc}, id);
	}
	
	public void buildFileDetailLayout(String authorName, String date, String name, String desc) {
		fileAuthor.setValue(authorName);
		fileDate.setValue(date);
		fileName.setValue(name);
		fileDesc.setValue(desc);
	}

	public HorizontalSplitPanel getHsplit() {
		return hsplit;
	}

}
