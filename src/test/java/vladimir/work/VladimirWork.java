package vladimir.work;


import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class VladimirWork {

	public static WebDriver driver = null;
	public Select vacancyDropdown = null;
	@BeforeTest
	public void beforeClass() {
	    WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://vladimirwork.github.io/web-ui-playground/");
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
	}
  
  @Test
  public void f() {
	  
	  sendKey("FirstName", "Gifty");
	  sendKey("LastName", "Chabbra");
	  sendKey("Email", "giftyChabra@gmail.com");
	  sendKey("PhoneNumber", "481122334455");
	  driver.findElement(By.xpath(".//input[@value='Female']")).click();
	  
	  vacancyDropdown = new Select(driver.findElement(By.name("Vacancy")));
	  vacancyDropdown.selectByValue("Business Analyst");
	  
	  driver.findElement(By.name("Agreement")).click();
	  driver.findElement(By.name("myfile")).
	  sendKeys(System.getProperty("user.dir") + "\\payload\\yourEmptyResume.pdf");
	  driver.findElement(By.name("submitbutton")).submit(); 
	  System.out.println("Your Details " + driver.switchTo().alert().getText() + " Submitted Successfully.");
	  driver.switchTo().alert().accept();
  }


  @AfterTest
  public void afterClass() throws InterruptedException {
	  driver.close();
  }
  
  public void sendKey(String locator, String value){
	  driver.findElement(By.name(locator)).sendKeys(value);
  }

}
