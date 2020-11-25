package com.mayab.calidad.doubles;

public interface DAO {
	
	boolean addAlumno(Alumno a);
	
	boolean deleteAlumno(Alumno a);
	
	boolean updatePromedio(Alumno a, double nuevoPromedio);
	
	int getNumeroAlumnos();
	
	Alumno getAlumno(int id);
	
}
