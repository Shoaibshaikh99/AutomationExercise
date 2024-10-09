package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	
	//Constructor
	public BasePage(WebDriver driver) {
		
		this.driver = driver;  //WebDriver object is storing in instance variable
		PageFactory.initElements(driver, this); //PageFactory initializes driver for @FindBy Annotation
	}
}
