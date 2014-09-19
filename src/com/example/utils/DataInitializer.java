package com.example.utils;

import com.example.domain.Career;
import com.example.domain.Subject;
import com.example.domain.User;
import com.example.persistence.CareerManager;
import com.example.persistence.SubjectManager;
import com.example.persistence.UserManager;

public class DataInitializer {
	
	private static CareerManager careerMngr = new CareerManager();
	private static UserManager userMngr = new UserManager();
	private static SubjectManager subjectMngr = new SubjectManager(); 
	
	public DataInitializer() {
		
	}
	
	public static void populateTables() {
		
		populateCareerTable();
		populateUsersTable();
		populateSubjectsTable();
		
	}
	
	private static void populateCareerTable() {
		Career c = new Career();
		String[] names = {"Ingeniería en Sistemas", "Ingeniería Química", 
				"Ingeniería Eléctrica", "Ingeniería Mecánica"};
		for (int i = 1; i < 5; i++) {
			c.setId(i);
			c.setName(names[i - 1]);
			careerMngr.save(c);
		}
	}
	
	private static void populateUsersTable(){

		User user1 = new User();
		user1.setId(13122717217802891560D);
		user1.setName("Jesus");
		user1.setLastName("Biaggioni");
		user1.setEmail("jesu772@gmail.com");
		userMngr.save(user1);
		
	}
	
	private static void populateSubjectsTable() {
		
		String[] s1 = {"Algoritmos Y Estructuras De Datos", "Matematica Discreta", 
				"Sistemas Y Organizaciones", "Fisica I", "Álgebra y Geometría Analítica", 
				"Análisis Matemático I", "Ingeniería y Sociedad", "Inglés I", "Química"};
		String[] s2 = {"Analisis De Sistemas", "Arquitectura De Computadoras", 
				"Paradigmas De Programacion", "Sintaxis Y Semantica De Los Lenguajes", 
				"Sistemas De Representación", "Analisis Matematico II", "Inglés II", 
				"Fisica II", "Probabilidades y Estadistica"};
		String[] s3 = {"Comunicaciones", "Diseño De Sistemas", "Economía", 
				"Estadistica Aplicada", "Gestion De Datos", "Matematica Superior", 
				"Metodos Numericos", "Sistemas Operativos"};
		String[] s4 = {"Investigación Operativa", "Ingeniería De Software", 
				"Administración De Recursos", "Electronica General y Aplicada", 
				"Redes De Información", "Simulacion", "Teoria De Control", "Legislación"};
		String[] s5 = {"Administración Gerencial", "Inteligencia Artificial", 
				"Control Estadístico De Procesos", "Laboratorio De Microprocesadores Y DSP", 
				"Procesamiento Digital De Imagenes", "Proyecto Final", 
				"Redes De Fibra Óptica De Segunda Generación", "Redes Neuronales", 
				"Sistemas Informaticos Industriales", "Sistemas De Gestion Integral"};
		String[][] sis = {s1, s2, s3, s4, s5};
		
		String[] q1 = {"Quimica 1º"};
		String[] q2 = {"Quimica 2º"};
		String[] q3 = {"Quimica 3º"};
		String[] q4 = {"Quimica 4º"};
		String[] q5 = {"Quimica 5º"};
		String[][] qui = {q1, q2, q3, q4, q5};
		
		String[] e1 = {"Electrica 1º"};
		String[] e2 = {"Electrica 2º"};
		String[] e3 = {"Electrica 3º"};
		String[] e4 = {"Electrica 4º"};
		String[] e5 = {"Electrica 5º"};
		String[][] ele = {e1, e2, e3, e4, e5};
		
		String[] m1 = {"Mecánica 1º"};
		String[] m2 = {"Mecánica 2º"};
		String[] m3 = {"Mecánica 3º"};
		String[] m4 = {"Mecánica 4º"};
		String[] m5 = {"Mecánica 5º"};
		String[][] mec = {m1, m2, m3, m4, m5};
		
		String[][][] subs = {sis, qui, ele, mec};
		
		
		for (int j = 0; j < 4; j++) {
			int id = j + 1;
			Career ca = careerMngr.getById(id);
			for (int i = 0; i < 5; i++) {
				int year = i + 1; 
				for (String sn : subs[j][i]) {
					Subject sub = new Subject();
					sub.setName(sn);
					sub.setYear(year);
					sub.setCareer(ca);
					subjectMngr.save(sub);
				}
			}
		}
	}
	
}
