package com.mayab.calidad.doubles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlumnoDAOOracle implements DAO {

	public boolean addAlumno(Alumno a) {
		
		Connection c = getConnection();
		PreparedStatement s;
		
		try {
			s = c.prepareStatement("INSERT INTO alumno(id, nombre,edad, calificacion, email) VALUES (?, ?, ?, ?, ?)");
			s.setInt(1, a.getId());
			s.setString(2,  a.getNombre());
			s.setInt(3, a.getEdad());
			s.setDouble(4, a.getCalificacion());
			s.setString(5, a.getEmail());
			s.executeUpdate();
			c.close();
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}

	public boolean deleteAlumno(Alumno a) {
		
		Connection c = getConnection();
		PreparedStatement s;
		
		try {
			s = c.prepareStatement("DELETE FROM alumno WHERE id = ?");
			s.setInt(1, a.getId());
			s.executeUpdate();
			c.close();
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}

	public boolean updatePromedio(Alumno a, double nuevoPromedio) {
		Connection c = getConnection();
		PreparedStatement s;
		
		try {
			s = c.prepareStatement("UPDATE alumno SET calificacion = ? WHERE id = ?");
			s.setDouble(1, nuevoPromedio);
			s.setInt(2, a.getId());
			s.executeUpdate();
			c.close();
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		return false;
	}

	public int getNumeroAlumnos() {
		
		Connection c = getConnection();
		PreparedStatement s;
		int numero_alumnos = 0;
		
		try {
			s = c.prepareStatement("SELECT * from alumno");
			numero_alumnos = s.executeUpdate();
			c.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}

		return numero_alumnos;
	}

	public Alumno getAlumno(int id) {
		
		Connection c = getConnection();
		PreparedStatement s;
		ResultSet r;
		Alumno alumno = null;
		
		try {
			s = c.prepareStatement("SELECT * from alumno WHERE id = ?");
			s.setInt(1, id);
			r = s.executeQuery();
			r.next();
			
			int alumno_db_id = r.getInt(1);
			String alumno_db_name = r.getString(2);
			int alumno_db_age = r.getInt(3);
			int alumno_db_grade = r.getInt(4);
			String alumno_db_email = r.getString(5);

			alumno = new Alumno(alumno_db_id, alumno_db_name, alumno_db_age, alumno_db_grade, alumno_db_email);
			
			r.close();
			s.close();
			c.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return alumno;
	}
	
	public Connection getConnection() {
		
		Connection c = null;  
        try{  
        	Class.forName("oracle.jdbc.driver.OracleDriver");  
            c = DriverManager.getConnection("jdbc:hr:hr@//localhost:1521/xepdb1","system","hr");
        }catch(Exception e){
        	System.out.println(e);
        }  
        return c;  
	}

}

