package com.mayab.calidad.dbunit;

public class ALumnoDriver{
	public static void main(String[] args) {
		AlumnoDAOOracle a = new AlumnoDAOOracle();
		a.getConnection();
		Alumno x = new Alumno();
		a.addAlumno(x);
		
	}
}