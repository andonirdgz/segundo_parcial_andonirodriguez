package com.mayab.calidad.doubles;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


public class TestDAO {


	private DAOOracle db;
	private Alumno a;
	
	@Before 
	public void setupMocks() {
		db = mock(DAOOracle.class);
		a = new Alumno(1,"test", 21, 9.5, "test@gmail.com");
		setupDatabase();
		addAlumnos();
	}
	
	public void setupDatabase() {
		when(db.getDB()).thenReturn(new HashMap<Integer, Alumno>());
	}
	
	public void addAlumnos() {
		
		when(db.addAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			 Alumno arg = (Alumno) invocation.getArguments()[0];
			 
		
			 db.getDB().putIfAbsent(arg.getId(), arg);
			 return true;
			}
			
		});
		
	}
	
	@Test
	public void testAdd() {
		int size = db.getDB().size();
		
		db.addAlumno(a);
		
		assertThat(db.getDB().size(), is(size + 1));
	}
	
	@Test
	public void testDelete() {
		
		when(db.deleteAlumno(any(Alumno.class))).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			 Alumno arg = (Alumno) invocation.getArguments()[0];
			 
			 if(db.getDB().containsKey(arg.getId())) {
				 db.getDB().remove(arg.getId());
				 return true;
			 }
			 	return false;
			}
			
		});
		
		db.addAlumno(a);
		int size = db.getDB().size();
		db.deleteAlumno(a);
		
		assertThat(db.getDB().size(), is(size - 1));
	}
	
	@Test
	public void testUpdate() {

		when(db.updatePromedio(any(Alumno.class), anyDouble())).thenAnswer(new Answer<Boolean>() {
			
			public Boolean answer(InvocationOnMock invocation) throws Throwable {
			 Alumno arg = (Alumno) invocation.getArguments()[0];
			 Double arg1 = (Double) invocation.getArguments()[1];
			 
			 if(db.getDB().containsKey(arg.getId())) {
				 Alumno a = db.getDB().get(arg.getId());
				 a.setCalificacion(arg1);
				 db.getDB().put(a.getId(), a);	 
				 return true;
			 }
			 	return false;
			}
			
		});

		db.addAlumno(a);
		double cal = db.getDB().get(a.getId()).getCalificacion();
		db.updatePromedio(a, 8.3);
		cal = db.getDB().get(a.getId()).getCalificacion();
		
		assertThat(cal, is(a.getCalificacion()));
	}

}
