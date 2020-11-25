package com.mayab.calidad.doubles;

import java.util.HashMap;

public class DAOOracle implements DAO {
	
	private HashMap<Integer, Alumno> db = new HashMap<Integer, Alumno>();

	@Override
	public boolean addAlumno(Alumno a) {
		return false;
	}

	@Override
	public boolean deleteAlumno(Alumno a) {
		return false;
	}

	@Override
	public boolean updatePromedio(Alumno a, double nuevoPromedio) {
		return false;
	}

	@Override
	public int getNumeroAlumnos() {
		return 0;
	}

	@Override
	public Alumno getAlumno(int id) {
		return null;
	}
	
	public HashMap<Integer, Alumno> getDB(){
		return db;
	}
}
