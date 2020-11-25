package com.mayab.calidad.dbunit;

public class Alumno {
	private int id;
	private String nombre;
	private int edad;
	private double calificacion;
	private String email;
	
	public Alumno() {
		id = 1;
		nombre = "Andoni";
		edad = 20;
		calificacion = 8.5;
		email = "andoni_rdgz@outlook.com";
	}
	
	public Alumno(int i, String string, int j, double d, String string2) {
		id = i;
		nombre = string;
		edad = j;
		calificacion = d;
		email = string2;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public double getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(double calificacion) {
		this.calificacion = calificacion;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
		
}
