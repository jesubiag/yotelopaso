package com.example.views;

import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.TextField;



public interface  DatosReg extends AbstractHomeView {
	
	interface DatosRegListener extends AbstractHomeViewListener {
		public void componentAttachedToContainer(String caption);
		void buttonClick(String caption);
	}
	
	public void addListener(DatosRegListener listener);
	public ComboBox getCarr();
	public void setCarr(ComboBox carr);
	public ComboBox getTaño();
	public void setTaño(ComboBox taño);
	public DateField getFecha();
	public void setFecha(DateField fecha);
	public TextField getTel();
	public void setTel(TextField tel);
	public TextField getCiu();
	public void setCiu(TextField ciu);
	public Button getAceptar();
	public void setAceptar(Button aceptar);
	public Button getCancelar();
	public void setCancelar(Button cancelar);
}
