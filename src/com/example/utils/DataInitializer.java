package com.example.utils;

import java.util.Date;

import com.example.domain.Career;
import com.example.domain.News;
import com.example.domain.Subject;
import com.example.domain.User;
import com.example.persistence.CareerManager;
import com.example.persistence.NewsManager;
import com.example.persistence.SubjectManager;
import com.example.persistence.UserManager;

public class DataInitializer {
	
	private static CareerManager careerMngr = new CareerManager();
	private static UserManager userMngr = new UserManager();
	private static SubjectManager subjectMngr = new SubjectManager(); 
	private static NewsManager newsMngr = new NewsManager();
	
	public DataInitializer() {
		
	}
	
	public static void populateTables() {
		
		populateCareerTable();
		populateUsersTable();
		populateSubjectsTable();
		populateNewsTable();
		
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
		user1.setCareer(careerMngr.getById(1));
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
		
		String[] q1 = {"Algebra", "Química General", "Ingeniería y Sociedad", 
				"Fundamentos de Informática", "Integración I", "Física I", "Análisis Matemático I", 
				"Ingles Técnico I"};
		String[] q2 = {"Integración II", "Inglés Técnico II", "Química Orgánica", 
				"Probabilidad y Estadística", "Análisis Matemático II", "Física II", 
				"Química Inorgánica", "Sistemas de Representación"};
		String[] q3 = {"Mecánica Eléctrica Industrial", "Matemática Superior Aplicada", 
				"Química Analítica", "Fisicoquímica", "Fenómenos de transporte", 
				"Integración III", "Física III", "Mecánica de Fluidos y Fisicoquímica Aplicada", "Termodinámica"};
		String[] q4 = {"Operaciones II", "Ingeniería de las Reacciones Químicas", "Operaciones Unitarias I", 
				"Sistemas de Instalaciones Industriales", "Legislación", "Tecnología de la Energía Térmica", 
				"Integración IV", "Economía", "Control Estadístico de Procesos", "Química Analítica Aplicada"};
		String[] q5 = {"Ciencia e Ingeniería de los Materiales", "Control Automático de Procesos", 
				"Integración V", "Modelización, Simulación y optimización de Procesos Químicos", 
				"Organización Industrial", "Ciencia e Ingeniería de los Materiales", 
				"Biotecnología", "Ingeniería Ambiental"};
		String[][] qui = {q1, q2, q3, q4, q5};
		
		String[] e1 = {"Fundamentos de Informática", "Análisis Matemático I", "Física I", 
				"Ingles I", "Algebra", "Integración Eléctrica I", "Química General", 
				"Ingeniería y Sociedad"};
		String[] e2 = {"Ingles II", "Análisis Matemático II", "Estabilidad", 
				"Integración Eléctrica II", "Electrotecnia I", "Física II", 
				"Probabilidad y Estadística", "Calculo Numérico", "Sistemas de Representación"};
		String[] e3 = {"Maquinas Eléctricas I", "Legislación", "Instrumentos y Mediciones Eléctricas", 
				"Fundamento para el Análisis de Señales", "Tecnología y Ensayo de Materiales Eléctricos", 
				"Física III", "Teoría de los Campos", "Electrotecnia II", "Termodinámica", "Mecánica Técnica"};
		String[] e4 = {"Maquinas Eléctricas II", "Instalaciones Eléctricas y Luminotecnia", 
				"Economía", "Electrónica I", "Seguridad y Riesgo Eléctrico", "Control Automático", 
				"Maquinas Térmicas, Hidráulicas y de Fluidos"};
		String[] e5 = {"Organización y Administración de Empresas", "Instrumentación", 
				"Accionamiento y Controles Eléctricos", "Proyecto Final", 
				"Generación, Transmisión y Distribución de la energía eléctrica", 
				"Sistemas de Potencia", "Electrónica II", "Tecnología Mecánica", "Electrónica Aplicada"};
		String[][] ele = {e1, e2, e3, e4, e5};
		
		String[] m1 = {"Física I", "Análisis Matemático I", "Fundamentos de Informática", 
				"Algebra", "Ingeniería Mecánica I", "Química", "Ingles I", "Sistemas de Representación", 
				"Ingeniería y Sociedad"};
		String[] m2 = {"Ingles II", "Estabilidad I", "Materiales Metálicos", "Física II", 
				"Calculo Numérico", "Química Aplicada", "Análisis Matemático II", 
				"Ingeniería Ambiental y Seguridad Industrial", "Ingeniería Mecánica II"};
		String[] m3 = {"Física III", "Termodinámica", "Calculo Avanzado", "Estabilidad II", 
				"Mecánica Racional", "Probabilidad y Estadística", "Diseño Mecánico", 
				"Mediciones y Ensayos", "Ingeniería Mecánica III"};
		String[] m4 = {"Electrónica y Sistemas de Control", "Economía", "Elementos de Maquinas", 
				"Tecnología del Calor", "Electrónica y Maquinas Eléctricas", "Mecánica de los Fluidos"};
		String[] m5 = {"Maquinas Alternativas y Turbo máquinas", "Mantenimiento", 
				"Instalaciones Industriales", "Legislación", "Proyecto Final", 
				"Metrología e Ingeniería de la Calidad", "Tecnología de Fabricación"};
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
	
	private static void populateNewsTable() { 
		News news = new News();
		news.setCareer(careerMngr.getById(1));
		news.setContent("El parcial del miércoles 1/10 se pasa al viernes 3/10.");
		news.setDate(new Date());
		news.setSubject(subjectMngr.getById(32));
		newsMngr.save(news);
	}
	
}
