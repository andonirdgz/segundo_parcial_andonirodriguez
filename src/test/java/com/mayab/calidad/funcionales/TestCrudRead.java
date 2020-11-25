package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCrudRead {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	// Chrome driver setup
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\andon\\Documents\\Tareas de quinto semestre\\Software tests and QA\\chromedriver.exe");
	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  
  // Pause function
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }

  // Delete test
  @Test
  public void testMERNRead() throws Exception {
	System.out.println("Test Read\n");
	
	// Expected values
	String[] expected_values = {"Add", "add@gmail.com", "21", "m"};
	
	// Retrieved values
	String[] retrieved_values = new String[4];
	
	
	driver.get("https://mern-crud.herokuapp.com/");
	
	// Get web table with all contents
    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
    
    pause(3000);
    
    // make a list with the table rows
    List<WebElement> rows = table.findElements(By.tagName("tr"));
    
    // Get text values of each cell except for the first row which is the columns title row
    for(int i = 1; i < rows.size(); i++) {
    	
    	List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
   
    	for(int j = 0; j < columns.size()-1; j++) retrieved_values[j] = columns.get(j).getText();
    	
    	// if retrieved values are equal as expected break the loop
    	if(Arrays.equals(expected_values, retrieved_values)) break;
    	
    }
    
    // Check for every text cell if is the same as the added in the previous test case which are the expected values
    for(int i = 0; i < expected_values.length; i++) assertEquals(expected_values[i], retrieved_values[i]);
    
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
