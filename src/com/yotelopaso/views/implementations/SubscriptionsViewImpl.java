package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.AbstractSelect.ItemCaptionMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.presenters.SubscriptionsPresenter;
import com.yotelopaso.views.SubscriptionsView;
import com.yotelopaso.views.implementations.templates.AbstractHomeViewImpl;

public class SubscriptionsViewImpl extends AbstractHomeViewImpl implements SubscriptionsView, ClickListener {

	private static final long serialVersionUID = 6723936781106918640L;
	final private VerticalLayout mainLayout = new VerticalLayout();
	private SubscriptionsPresenter presenter;
	private Panel panel;
	private Panel optionGroups;
	final private VerticalLayout ogsLayout = new VerticalLayout();
	final private OptionGroup og1 = new OptionGroup("Primer Año");
	final private OptionGroup og2 = new OptionGroup("Segundo Año");
	final private OptionGroup og3 = new OptionGroup("Tercer Año");
	final private OptionGroup og4 = new OptionGroup("Cuarto Año");
	final private OptionGroup og5 = new OptionGroup("Quinto Año");
	private OptionGroup[] ogs = {og1, og2, og3, og4, og5};
	private BeanContainer<Integer, Subject> c1;
	private BeanContainer<Integer, Subject> c2;
	private BeanContainer<Integer, Subject> c3;
	private BeanContainer<Integer, Subject> c4;
	private BeanContainer<Integer, Subject> c5;
	private BeanContainer<Integer, Subject>[] containers;
	final private Button save = new Button("Guardar");
	final private Button cancel = new Button("Cancelar");
	
	@SuppressWarnings("unchecked")
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		
		c1 = new BeanContainer<Integer, Subject>(Subject.class);
		c2 = new BeanContainer<Integer, Subject>(Subject.class);
		c3 = new BeanContainer<Integer, Subject>(Subject.class);
		c4 = new BeanContainer<Integer, Subject>(Subject.class);
		c5 = new BeanContainer<Integer, Subject>(Subject.class);
		
		panel = new Panel("Suscripciones");
		panel.setSizeFull();
		panel.setStyleName("user-test");
		rightLayout.addComponent(panel);
		rightLayout.setExpandRatio(panel, 1.0f);
		
		mainLayout.removeAllComponents();
		mainLayout.setSizeFull();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		panel.setContent(mainLayout);
		
		optionGroups = new Panel();
		optionGroups.addStyleName(ValoTheme.PANEL_BORDERLESS);
		optionGroups.setSizeFull();
		
		ogsLayout.setSizeUndefined();
		ogsLayout.setSpacing(true);
		optionGroups.setContent(ogsLayout);
		
		setContainer(c1, c2, c3, c4, c5);
		
		setOptionGroupsProperties(ogs);
		presenter.getData();
		presenter.setData();
		presenter.setSelectedSubjects();
		
		save.addClickListener(this);
		cancel.addClickListener(this);
		
		ogsLayout.addComponents(ogs);
		
		HorizontalLayout botones = new HorizontalLayout();
		botones.setSizeUndefined();
		botones.addComponents(save,cancel);
		
		mainLayout.addComponents(optionGroups);
		mainLayout.addComponent(botones);
		
		mainLayout.setExpandRatio(optionGroups, 1.0f);
	}
	
	List<SubscriptionsViewListener> listeners = new ArrayList<SubscriptionsViewListener>();

	@Override
	public void addListener(SubscriptionsViewListener listener) {
		listeners.add(listener);
	}
	
	private void setOptionGroupsProperties(OptionGroup... groups) {
		for (int i=0; i < groups.length; i++) {
			groups[i].setMultiSelect(true);
			groups[i].setSizeFull();
			groups[i].setImmediate(true);
			groups[i].setItemCaptionPropertyId("name");
			groups[i].setItemCaptionMode(ItemCaptionMode.PROPERTY);
		}
	}

	public SubscriptionsPresenter getPresenter() {
		return presenter;
	}
	
	@SuppressWarnings("unchecked")
	private void setContainer(BeanContainer<Integer, Subject>...beanContainers) {
		containers = beanContainers;
	}

	public void setPresenter(SubscriptionsPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setOptionGroupData(List<Subject> subjectList, int index) {
		containers[index].setBeanIdProperty("id");
		containers[index].addAll(subjectList);
		ogs[index].setContainerDataSource(containers[index]);
	}

	@Override
	public void selectSubjects(Integer year, Integer id) {
		if (id!=2000)
			ogs[year-1].select(id);
	}
	
	@Override
	public void buttonClick(ClickEvent event) {
		for (SubscriptionsViewListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption(), og1.getValue(), og2.getValue(), og3.getValue(), 
					og4.getValue(), og5.getValue());
		}
	}

	@Override
	public void showSuccesfullSaveNotification(String message) {
		Notification.show(message);
	}
}
