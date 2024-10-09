package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreated_DeletedPage extends BasePage{
	
	public AccountCreated_DeletedPage(WebDriver driver) {
		super(driver);
	}
	
	//WebElements
	@FindBy(xpath = "//h2[@class='title text-center']//b")
	WebElement msgAccountCreated_Deleted;
	
	@FindBy(xpath = "//a[normalize-space()='Continue']")
	WebElement btnContinue;
	
	//Action Methods 
	//TC001-14. Verify that 'ACCOUNT CREATED!' is visible
	//TC001-18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
	//I have used same element and methods for above two steps bcz xpath is same and i can use for both purposes
	public String VerifyAccountCreated_Deleted() {
		try {
			String text = msgAccountCreated_Deleted.getText();
			return text;
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
	
	public void clickContinue() {
		btnContinue.click();
	}

}
