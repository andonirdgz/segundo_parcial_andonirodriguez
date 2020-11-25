package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCrudDelete {
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

  @Test
  public void testMERNDelete() throws Exception {
	System.out.println("Test Delete");
	
	driver.get("https://mern-crud.herokuapp.com/");
	
	// Get web table with all contents
    WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table"));
    
    pause(3000);
    
    // make a list with the table rows before delete an element
    List<WebElement> rows_before_delete = table.findElements(By.tagName("tr"));
    System.out.println("Size before: " + (rows_before_delete.size()));
    
    // find the first delete button in the table and click it
    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[2]")).click();
    
    // wait for the confirmation dialog
    pause(1000);
    
    // click yes button in the confirmation dialog
    driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]")).click();
    
    // wait for table update
    pause(3000);
    
    // make a list with the table rows after deleting and element
    List<WebElement> rows_after_delete = table.findElements(By.tagName("tr"));
    System.out.println("Size after: " + rows_after_delete.size());
    
    // compare that the number of rows after are equal to the number of rows before -1
    assertEquals(rows_after_delete.size(), rows_before_delete.size()-1);
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