package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//WebElements
	@FindBy(xpath = "//a[normalize-space()='Home']")
	WebElement btnHome;
	
	@FindBy(xpath = "//a[normalize-space()='Signup / Login']")
	WebElement btnSignup_login;
	
	@FindBy(xpath = "//li[10]//a[1]")
	WebElement btnLoggedInAs;
	
	@FindBy(xpath = "//a[normalize-space()='Delete Account']")
	WebElement btnDeleteAccount;
	
	@FindBy(xpath = "//a[normalize-space()='Logout']")
	WebElement btnLogout;
	
	//Action Methods
	public boolean isHomePageDisplayed() {
		try {
			boolean status = btnHome.isDisplayed();
			return status;
		}catch (Exception e){
			return false;
		}
	}
	
	public boolean verifyLoginSuccess() {
		try {
			return(btnLoggedInAs.isDisplayed());
		}catch(Exception e) {
			return false;
		}
	}
	
	public void clickSignup_Login() {
		btnSignup_login.click();
	}
	
	public void clickDeleteAccount() {
		btnDeleteAccount.click();
	}
	
	public void clickLogout() {
		btnLogout.click();
	}
	
	public boolean isSignupBtnClicked() {
		try {	
			boolean btnStatus  = btnSignup_login.isEnabled();
			return btnStatus;
		}catch(Exception e) {
			return false;
		}
	}

}
