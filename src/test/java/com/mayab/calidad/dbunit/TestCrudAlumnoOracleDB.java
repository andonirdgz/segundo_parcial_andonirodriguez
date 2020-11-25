package com.mayab.calidad.dbunit;

import java.io.File;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

public class TestCrudAlumnoOracleDB extends DBTestCase {

	
	public TestCrudAlumnoOracleDB(String name) {
		super(name);
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "oracle.jdbc.driver.OracleDriver");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:oracle:thin:@localhost:1522:xe");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "dbunit");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "dbunit");	
	}
	
	// Setup
	@Before
	public void setUp() throws Exception{
		super.setUp();
		
		// Database connection
		IDatabaseConnection c = getConnection();
		
		try {
			DatabaseOperation.CLEAN_INSERT.execute(c, getDataSet());
		} finally {
			c.close();
		}
	}
	
	// Load initial database state
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new File("src/resources/init_db.xml"));
	}
	
	// Test Add
	@Test
	public void testAdd() {
		
		// Create Alumno (same as the expected in the .xml file)
		Alumno a = new Alumno(4, "Andoni", 21, 8, "andoni@outlook.com");
		AlumnoDAOOracle DAO = new AlumnoDAOOracle();
		
		DAO.addAlumno(a);
		
		try {
			
			// Get database data
			IDataSet db_dataset = getConnection().createDataSet();
			
			// Table with database data of alumno table
			ITable db_retrieved_table = db_dataset.getTable("alumno");
			
			
			IDataSet expected_dataset = new FlatXmlDataSetBuilder().build(new File("src/resources/test_add_expected.xml"));
			
			// Table with dataset of the .xml file
			ITable expected_table = expected_dataset.getTable("alumno");
			
			// Check if the expected table is equal as the returned of the database
			Assertion.assertEquals(expected_table, db_retrieved_table);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testDelete() {
		
		// Create the alumno that's gonna be deleted
		Alumno a = new Alumno(2, "test_2", 22, 6, "test2@hotmail.com");
		AlumnoDAOOracle DAO = new AlumnoDAOOracle();
		
		DAO.deleteAlumno(a);

		try {
			
			// Get database data
			IDataSet db_dataset = getConnection().createDataSet();
			
			// Table with database data of alumno table
			ITable db_retrieved_table = db_dataset.getTable("alumno");
			
			// Get dataset which is the data in the .xml file
			IDataSet expected_dataset = new FlatXmlDataSetBuilder().build(new File("src/resources/test_delete_expected.xml"));
			
			// Table with dataset of the .xml file
			ITable expected_table = expected_dataset.getTable("alumno");
			
			// Check if the expected table is equal as the returned by the database
			Assertion.assertEquals(expected_table, db_retrieved_table);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}

	@Test
	public void testRead() throws Exception {
		
		Alumno a = new Alumno(1, "test_1", 21, 5, "test1@hotmail.com");

		try {
			
			// Get dataset which is the data in the .xml file
			IDataSet expected_dataset = new FlatXmlDataSetBuilder().build(new File("src/resources/test_read_expected.xml"));
			
			// Table with dataset of the .xml file
			ITable expected_table = expected_dataset.getTable("alumno");
			
			// Get the alumno by id of the database
			ITable retrieved_data = getConnection().createQueryTable("result_alumno", "SELECT * FROM alumno WHERE id = " + a.getId());
			
			// Check if the expected alumno is the same as the returned by the database
			Assertion.assertEquals(expected_table, retrieved_data);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	@Test
	public void testUpdate() {
		
		Alumno a = new Alumno(3, "test_3", 21, 7, "test1@hotmail.com");
		AlumnoDAOOracle DAO = new AlumnoDAOOracle();
		
		// Update promedio with 10
		DAO.updatePromedio(a.getId(), 10);
		
		try {
			
			// Get dataset from databse
			IDataSet db_dataset = getConnection().createDataSet();
			
			// Table with database data from alumno table
			ITable db_retrieved_table = db_dataset.getTable("alumno");

			// Get dataset which is the data in the .xml file
			IDataSet expected_dataset = new FlatXmlDataSetBuilder().build(new File("src/resources/test_update_expected.xml"));
			
			// Table with dataset of the .xml file
			ITable expected_table = expected_dataset.getTable("alumno");
			
			// Check if the promedio of the expected alumno was successfully updated
			Assertion.assertEquals(expected_table, db_retrieved_table);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
}
 