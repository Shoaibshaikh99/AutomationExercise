package pageObjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class SignupPage extends BasePage {
	public SignupPage(WebDriver driver) {
		super(driver);
		
	}
	
	//WebElements
	@FindBy(xpath = "//b[normalize-space()='Enter Account Information']")
	WebElement msgConfirmation;
	
	@FindBy(xpath = "//div[@class='clearfix']//input")
	List<WebElement> titles;
	
	/*@FindBy(xpath = "//input[@id='name']")
	WebElement txtName;*/  //Name and email is already autofilled

	@FindBy(xpath = "//input[@id='password']")
	WebElement txtPassword;
	
	@FindBy(xpath = "//select[@id='days']")
	WebElement drpDayEle;
	
	@FindBy(xpath = "//select[@id='months']")
	WebElement drpMonthEle;
	
	@FindBy(xpath = "//select[@id='years']")
	WebElement drpYearEle;
	
	@FindBy(xpath = "//input[@id='newsletter']")
	WebElement chkNewsletter;
	
	@FindBy(xpath = "//input[@id='optin']")
	WebElement chkOffer;
	
	@FindBy(xpath = "//input[@id='first_name']")
	WebElement txtfName;
	
	@FindBy(xpath = "//input[@id='last_name']")
	WebElement txtlName;
	
	@FindBy(xpath = "//input[@id='company']")
	WebElement txtCompany;
	
	@FindBy(xpath = "//input[@id='address1']")
	WebElement txtAddress1;
	
	@FindBy(xpath = "//input[@id='address2']")
	WebElement txtAddress2;
	
	@FindBy(xpath = "//select[@id='country']")
	WebElement drpCountryEle;
	
	@FindBy(xpath = "//input[@id='state']")
	WebElement txtState;
	
	@FindBy(xpath = "//input[@id='city']")
	WebElement txtCity;
	
	@FindBy(xpath = "//input[@id='zipcode']")
	WebElement txtZipcode;
	
	@FindBy(xpath = "//input[@id='mobile_number']")
	WebElement txtNumber;
	
	@FindBy(xpath = "//button[normalize-space()='Create Account']")
	WebElement btnCreateAccount;

	
	//Action Methods
	
	//TC001-8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
	public String getSignupConfirmationMsg() {
		try {
			String txt = msgConfirmation.getText();
			return txt;
		}catch(Exception e) {
			return (e.getMessage());
		}
	}
	
	//TC001-9. Fill details: Title, Name, Email, Password, Date of birth
	public void enterAccountInformation(String mr_mrs,String pwd,String day,String month,
			String year) {
		
		//Select title basis on user input either Mr or Mrs
		for(WebElement title : titles) {
			if(title.getAttribute("value").equals(mr_mrs)) {
				title.click();
				break;
			}
		}
		
		txtPassword.sendKeys(pwd);
		
		//Select day from dropdown
		Select drpDay = new Select(drpDayEle);
		drpDay.selectByVisibleText(day);
		
		//Select month from dropdown
		Select drpMonth = new Select(drpMonthEle);
		drpMonth.selectByVisibleText(month);
		
		//Select year from dropdown
		Select drpYear = new Select(drpYearEle);
		drpYear.selectByVisibleText(year);
		
	}
	
	//TC001- 10. Select checkbox 'Sign up for our newsletter!'
	JavascriptExecutor js = (JavascriptExecutor) driver;
	
	public void clickNewsletterBox() {
		js.executeScript("arguments[0].click();", chkNewsletter);
	}
	
	//TC001-11. Select checkbox 'Receive special offers from our partners!'
	public void clickOfferBox() {
		js.executeScript("arguments[0].click();", chkOffer);
	}
	
	//TC002-12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
	public void EnterAddressDetails(String fname,String lname,String company,String add,
			String country,String state,String city,String pincode,String number) {
		txtfName.sendKeys(fname);
		txtlName.sendKeys(lname);
		txtCompany.sendKeys(company);
		txtAddress1.sendKeys(add);
		txtAddress2.sendKeys(add);
		
		//Select Country from dropdown
		Select drpCountry = new Select(drpCountryEle);
		drpCountry.selectByVisibleText(country);
		
		txtState.sendKeys(state);
		txtCity.sendKeys(city);
		txtZipcode.sendKeys(pincode);
		txtNumber.sendKeys(number);
	}
	
	//TC001-13. Click 'Create Account button'
	public void clickCreateAccount() {
		js.executeScript("arguments[0].click();", btnCreateAccount);
		
	}

}
