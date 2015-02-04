package com.yotelopaso.views.implementations;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;




import com.vaadin.data.util.converter.StringToLongConverter;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.HasComponents.ComponentAttachListener;
import com.yotelopaso.views.DatosReg;
import com.yotelopaso.views.implementations.templates.AbstractHomeViewImpl;

public class DatosRegImpl extends AbstractHomeViewImpl implements DatosReg, ComponentAttachListener, ClickListener{

	private static final long serialVersionUID = 1L;
	ComboBox carr;
	ComboBox taño;
	DateField fecha;
	TextField tel;
	TextField ciu;
	Button aceptar;
	Button cancelar;
	Panel panel;
	
	public DatosRegImpl() {
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		super.enter(event);
		FormLayout form = new FormLayout();
		form.setCaption("Form");
		form.addStyleName("light");
		form.setWidth("500");
		form.setSpacing(true);
		fecha = new DateField("Fecha de nacimiento");
		carr = new ComboBox("Carrera");
		taño = new ComboBox("Año");
		taño.addItems(1,2,3,4,5);
		carr.setNullSelectionAllowed(false);
		taño.setNullSelectionAllowed(false);
		carr.setTextInputAllowed(false);
		taño.setTextInputAllowed(false);
		taño.setRequired(true);
		carr.setRequired(true);
		carr.setRequiredError("Obligatorio");
		taño.setRequiredError("Obligatorio");
		
		tel = new TextField("Teléfono");
		tel.setImmediate(true);
		StringToLongConverter convertidorPlano = new StringToLongConverter() {
		  
			private static final long serialVersionUID = 1L;

			protected java.text.NumberFormat getFormat(Locale locale) {
		        NumberFormat format = super.getFormat(locale);
		        format.setGroupingUsed(false);
		        return format;
		    };
		};
		tel.setConverter(convertidorPlano);
		tel.setConversionError("Por favor ingrese un número de teléfono válido");
		tel.setMaxLength(20);	
		tel.setValue(null);
		tel.setNullRepresentation("");
		
		ciu = new TextField("Ciudad");
		
		form.addComponent(carr);
		form.addComponent(taño);
		form.addComponent(ciu);
		form.addComponent(tel);
		form.addComponent(fecha);
		
		aceptar = new Button("Aceptar");
		aceptar.addClickListener(this);
		cancelar = new Button("Cancelar");
		cancelar.addClickListener(this);
		HorizontalLayout botones= new HorizontalLayout();
		botones.addComponent(aceptar);
		botones.addComponent(cancelar);
		form.addComponent(botones);
		
		panel = new Panel("Complete sus datos por favor");
		panel.addComponentAttachListener(this);
		panel.setSizeFull();
		panel.setContent(form);
		rightLayout.addComponent(panel);
		rightLayout.setExpandRatio(panel, 1.0f);
	}
	
	
	List<DatosRegListener> listeners = new ArrayList<DatosRegListener>();

	@Override
	public void addListener(DatosRegListener listener) {
		listeners.add(listener);
		
	}
	
	@Override
	public void componentAttachedToContainer(ComponentAttachEvent event) {
		for (DatosRegListener listener : listeners) {
			listener.componentAttachedToContainer(event.getComponent().getCaption());
		}
		
	}

	public void buttonClick(ClickEvent event) {
		for (DatosRegListener listener : listeners) {
			listener.buttonClick(event.getButton().getCaption());
		}
		
	}

	public ComboBox getCarr() {
		return carr;
	}

	public void setCarr(ComboBox carr) {
		this.carr = carr;
	}

	public ComboBox getTaño() {
		return taño;
	}

	public void setTaño(ComboBox taño) {
		this.taño = taño;
	}

	public DateField getFecha() {
		return fecha;
	}

	public void setFecha(DateField fecha) {
		this.fecha = fecha;
	}

	public TextField getTel() {
		return tel;
	}

	public void setTel(TextField tel) {
		this.tel = tel;
	}

	public TextField getCiu() {
		return ciu;
	}

	public void setCiu(TextField ciu) {
		this.ciu = ciu;
	}

	public Button getAceptar() {
		return aceptar;
	}

	public void setAceptar(Button aceptar) {
		this.aceptar = aceptar;
	}

	public Button getCancelar() {
		return cancelar;
	}

	public void setCancelar(Button cancelar) {
		this.cancelar = cancelar;
	}
	

}
