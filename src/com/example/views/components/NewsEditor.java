package com.example.views.components;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;

public interface NewsEditor {
	
	interface NewsEditorListener {

		void buttonClick(String caption);
		
	}
	
	public void addListener(NewsEditorListener listener);
	
	public ComboBox getMat();
	public void setMat(ComboBox mat);
	
	public TextField getCont();
	public void setCont(TextField contenido);
	
	public DateField getFecha();
	public void setFecha(DateField fecha);
	
	public TextField getTit();
	public void setTit(TextField titulo);
	
	public Button getAceptar();
	public void setAceptar(Button aceptar);
	
	public Button getCancelar();
	public void setCancelar(Button cancelar);

	void buttonClick(ClickEvent event);

}
