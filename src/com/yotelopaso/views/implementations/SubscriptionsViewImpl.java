package com.yotelopaso.views.implementations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.yotelopaso.domain.Subject;
import com.yotelopaso.presenters.SubscriptionsPresenter;
import com.yotelopaso.views.SubscriptionsView;
import com.yotelopaso.views.implementations.templates.AbstractHomeViewImpl;

public class SubscriptionsViewImpl extends AbstractHomeViewImpl implements SubscriptionsView, ClickListener {

	private static final long serialVersionUID = 6723936781106918640L;
	final private VerticalLayout mainLayout = new VerticalLayout();
	private SubscriptionsPresenter presenter;
	private Panel panel;
	final private OptionGroup og1 = new OptionGroup("Primer Año");
	final private OptionGroup og2 = new OptionGroup("Segundo Año");
	final private OptionGroup og3 = new OptionGroup("Tercer Año");
	final private OptionGroup og4 = new OptionGroup("Cuarto Año");
	final private OptionGroup og5 = new OptionGroup("Quinto Año");
	private OptionGroup[] ogs = {og1, og2, og3, og4, og5};
	private BeanItemContainer<Subject> c1 = new BeanItemContainer<Subject>(Subject.class);
	private BeanItemContainer<Subject> c2 = new BeanItemContainer<Subject>(Subject.class);
	private BeanItemContainer<Subject> c3 = new BeanItemContainer<Subject>(Subject.class);
	private BeanItemContainer<Subject> c4 = new BeanItemContainer<Subject>(Subject.class);
	private BeanItemContainer<Subject> c5 = new BeanItemContainer<Subject>(Subject.class);
	private BeanItemContainer<Subject>[] containers;
	final private Button save = new Button("Guardar");
	
	@SuppressWarnings("unchecked")
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		
		panel = new Panel("Suscripciones");
		panel.setSizeFull();
		rightLayout.addComponent(panel);
		rightLayout.setExpandRatio(panel, 1.0f);
		
		mainLayout.setSizeUndefined();
		mainLayout.setSpacing(true);
		mainLayout.setMargin(true);
		panel.setContent(mainLayout);
		
		setContainer(c1, c2, c3, c4, c5);
		
		setOptionGroupsProperties(ogs);
		presenter.getData();
		presenter.setData();
		presenter.setSelectedSubjects();
		
		save.addClickListener(this);
		
		mainLayout.addComponents(ogs);
		mainLayout.addComponent(save);
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
			//groups[i].setItemCaptionPropertyId("name");
			//groups[i].setItemCaptionMode(ItemCaptionMode.PROPERTY);
		}
	}

	public SubscriptionsPresenter getPresenter() {
		return presenter;
	}
	
	@SuppressWarnings("unchecked")
	private void setContainer(BeanItemContainer<Subject>...beanItemContainers) {
		containers = beanItemContainers;
	}

	public void setPresenter(SubscriptionsPresenter presenter) {
		this.presenter = presenter;
	}

	@Override
	public void setOptionGroupData(List<Subject> subjectList, int index) {
		containers[index].addAll(subjectList);
		//ogs[index].setContainerDataSource(containers[index]);
		ogs[index].addItems(subjectList);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void selectSubjects(Set<Subject> subjects) {
		Collection<Subject> c1 = (Collection<Subject>) og1.getItemIds();
		Collection<Subject> c2 = (Collection<Subject>) og2.getItemIds();
		Collection<Subject> c3 = (Collection<Subject>) og3.getItemIds();
		Collection<Subject> c4 = (Collection<Subject>) og4.getItemIds();
		Collection<Subject> c5 = (Collection<Subject>) og5.getItemIds();
		for (Subject s : subjects) {
			for (Subject a : c1) {
				if (s.getId() == a.getId() ) {
					og1.select(a);
				}
			}
			for (Subject a : c2) {
				if (s.getId() == a.getId() ) {
					og2.select(a);
				}
			}
			for (Subject a : c3) {
				if (s.getId() == a.getId() ) {
					og3.select(a);
				}
			}
			for (Subject a : c4) {
				if (s.getId() == a.getId() ) {
					og4.select(a);
				}
			}
			for (Subject a : c5) {
				if (s.getId() == a.getId() ) {
					og5.select(a);
				}
			}
		}
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
