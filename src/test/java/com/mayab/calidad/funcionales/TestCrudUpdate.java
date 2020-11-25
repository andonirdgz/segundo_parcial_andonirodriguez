package com.mayab.calidad.funcionales;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCrudUpdate {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\andon\\Documents\\Tareas de quinto semestre\\Software tests and QA\\chromedriver.exe");
	driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  private void pause(long mils) {
	  try {
		  Thread.sleep(mils);
	  }catch(Exception e){
		  e.printStackTrace();
	  }
  }


  @Test
  public void testMERNUpdate() throws Exception {
	
	System.out.println("Test update");
    driver.get("https://mern-crud.herokuapp.com/");
    
    // Click edit button
    driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[5]/button[1]")).click();
    
    // Edit name
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("update");
    
    // Edit email
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("update@gmail.com");
    
    // Edit age
    driver.findElement(By.name("age")).click();
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("50");
    
    // Edit gender
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/i")).click();
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[3]/span")).click();
    
    // Click save button
    driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button")).click();
    
    // wait for alert message
    pause(3000);
    
    // Check if the alert text if the right one
    String text = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
    assertEquals(text, "Successfully updated!");
    
    System.out.println("Given alert message: " + text + "\n");
    
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