package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Signup_login_Page extends BasePage{
	
	public Signup_login_Page(WebDriver driver) {
		super(driver);
	}
	
	//WebElements
	
	//For signup
	@FindBy(xpath = "//h2[normalize-space()='New User Signup!']")
	WebElement msgSignup;
	
	@FindBy(xpath = "//input[@placeholder='Name']")
	WebElement txtName;
	
	@FindBy(xpath = "//input[@data-qa='signup-email']")
	WebElement txtSignupEmail;
	
	@FindBy(xpath = "//button[normalize-space()='Signup']")
	WebElement btnSignup;
	
	//For login
	@FindBy(xpath = "//h2[normalize-space()='Login to your account']")
	WebElement msgLogin;

	@FindBy(xpath = "//input[@data-qa='login-email']")
	WebElement txtLoginEmail;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement btnLogin;
	
	@FindBy(xpath = "//p[normalize-space()='Your email or password is incorrect!']")
	WebElement loginErrorMessage;
	
	//Action Methods
	public String getSignupMsg() {
		try {
			String txt = msgSignup.getText();
			return txt;
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	public void fillSignupDetails(String name,String email) {
		txtName.sendKeys(name);
		txtSignupEmail.sendKeys(email);
		
	}
	
	public void clickSignup() {
		btnSignup.click();
	}
	
	public String getLoginMsg() {
		try {
			String txt = msgLogin.getText();
			return txt;
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
	
	public void fillLoginDetails(String email,String pwd) {
		txtLoginEmail.sendKeys(email);
		txtPassword.sendKeys(pwd);
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
	public String getLoginErrorMessage() {
		try {
			String errorText = loginErrorMessage.getText();
			return errorText;
		}catch(Exception e) {
			return(e.getMessage());
		}
	}
	

}
