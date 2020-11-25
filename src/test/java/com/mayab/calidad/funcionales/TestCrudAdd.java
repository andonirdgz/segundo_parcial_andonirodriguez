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

public class TestCrudAdd {
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
  public void testMERNAdd() throws Exception {
	  
	System.out.println("Test Add");
	
    driver.get("https://mern-crud.herokuapp.com/");
    
    // Click add new button
    driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
    
    // Fill name
    driver.findElement(By.name("name")).click();
    driver.findElement(By.name("name")).clear();
    driver.findElement(By.name("name")).sendKeys("Add");
    
    // Fill email
    driver.findElement(By.name("email")).click();
    driver.findElement(By.name("email")).clear();
    driver.findElement(By.name("email")).sendKeys("add@gmail.com");
    
    // Fill age
    driver.findElement(By.name("age")).click();
    driver.findElement(By.name("age")).clear();
    driver.findElement(By.name("age")).sendKeys("21");
    
    // Chose gender
    driver.findElement(By.xpath("//div[3]/div[2]/div/i")).click();
    driver.findElement(By.xpath("//div[2]/div/div[2]/div")).click();
    
    // Click add button 
    driver.findElement(By.xpath("//form/button")).click();
    
    // Wait to alert message
    pause(3000);
    
    // Get alert text and check if its the right message
    String text = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/div")).getText();
    assertEquals(text, "Nice one!");
    
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

