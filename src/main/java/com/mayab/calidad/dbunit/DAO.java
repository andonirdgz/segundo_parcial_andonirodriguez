package com.mayab.calidad.dbunit;

public interface DAO {
	
	boolean addAlumno(Alumno a);
	
	boolean deleteAlumno(Alumno a);
	
	boolean updatePromedio(int id, double nuevoPromedio);
	
	int getNumeroAlumnos();
	
	Alumno getAlumno(int id);
	
}
