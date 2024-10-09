package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.AccountCreated_DeletedPage;
import pageObjects.HomePage;
import pageObjects.SignupPage;
import pageObjects.Signup_login_Page;
import testBase.BaseClass;

/*In every test cases there are two first test steps which are common and executing from BaseClass
which are mentioned in @BeforeClass. Here we have extended BaseClass. So Automatically 
@BeforeClass and @AfterClass will get executed.

1. Launch browser
2. Navigate to url 'http://automationexercise.com'

Test Case 1: Register User

1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'New User Signup!' is visible
6. Enter name and email address
7. Click 'Signup' button
8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
9. Fill details: Title, Name, Email, Password, Date of birth
10. Select checkbox 'Sign up for our newsletter!'
11. Select checkbox 'Receive special offers from our partners!'
12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
13. Click 'Create Account button'
14. Verify that 'ACCOUNT CREATED!' is visible
15. Click 'Continue' button
16. Verify that 'Logged in as username' is visible
17. Click 'Delete Account' button
18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button*/

public class TC_001_RegisterUserTest extends BaseClass{
	
	public static String email;
	public static String password;

	@Test(priority=1,groups="Regression")
	public void verifyHomePageIsVisible() {
		 SoftAssert softAssert = new SoftAssert();
		 HomePage hp = new HomePage(driver);
		 try {
			 
			logger.info("***** Starting TC_001_RegisterUserTest: verifyHomePageIsVisible *****");
			logger.debug("This is a debug log message");

			//3. Verify that home page is visible successfully
			boolean hpStatus = hp.isHomePageDisplayed();
			softAssert.assertEquals(hpStatus, true, "Home button is not selected or displayed");
			
			logger.info("Home page is displayed successfully.");
			
		}catch(Exception e) {
			logger.error("Test Failed: "+e.getMessage());
			//softAssert.fail("Test Failed: "+e.getMessage());
		}
		finally {
			// Collect all the assertion results which are present in each @Test method
	        softAssert.assertAll();
		}
	}
		
	@Test(priority=2,groups="Sanity")
	public void verifyAccountCreated() {
		 SoftAssert softAssert = new SoftAssert();
			
			 HomePage hp = new HomePage(driver); Signup_login_Page slp = new
			 Signup_login_Page(driver); SignupPage sp = new SignupPage(driver);
			 AccountCreated_DeletedPage ac = new AccountCreated_DeletedPage(driver);
			 
		try {
			logger.info("***** Starting TC_001_RegisterUserTest: verifyAccountCreated *****");
			
			//4. Click on 'Signup / Login' button
			hp.clickSignup_Login();
			
			//5. Verify 'New User Signup!' is visible
			
			String signupText = slp.getSignupMsg();
			softAssert.assertEquals(signupText,"New User Signup!", "Signup Text Mismatch");
			
			//6. Enter name and email address
			email = randomString()+"@gmail.com";
			slp.fillSignupDetails(randomString(), email);
			
			//7. Click 'Signup' button
			slp.clickSignup();
			
			//8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
			String signupText1 = sp.getSignupConfirmationMsg();
			softAssert.assertEquals(signupText1, "ENTER ACCOUNT INFORMATION","Signup Verification Message Mismatch");
			
			//9. Fill details: Title, Name, Email, Password, Date of birth
			password = "A"+randomAlphaNumeric();
			sp.enterAccountInformation("Mr",password, "5", "November",getRandomYear());
			
			//10. Select checkbox 'Sign up for our newsletter!'
			sp.clickNewsletterBox();
			
			//11. Select checkbox 'Receive special offers from our partners!'
			sp.clickOfferBox();
			
			//12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
			sp.EnterAddressDetails(randomString(),randomString(),randomString(),randomString(),
			getRandomCountry(),getRandomState(),getRandomCity(),getRandomPincode(),
			randomNumber());
			
			//13. Click 'Create Account button'
			sp.clickCreateAccount();
			
			//14. Verify that 'ACCOUNT CREATED!' is visible
			
			String accountCreatedTxt = ac.VerifyAccountCreated_Deleted();
			softAssert.assertEquals(accountCreatedTxt,"ACCOUNT CREATED!","Account Creation Failure");
			
			logger.info("Account Created Successfully");
			
			//15. Click 'Continue' button
			ac.clickContinue();
			
			//16. Verify that 'Logged in as username' is visible
			boolean loginStatus = hp.verifyLoginSuccess();
			softAssert.assertEquals(loginStatus,true,"Login Unsuccessful/Failed");
			
			
		}catch(Exception e) {
			logger.error("Test Failed: "+e.getMessage());
			//softAssert.fail("Test Failed: "+e.getMessage());
		}
		finally {
			// Collect all the assertion results which are present in each @Test method
	        softAssert.assertAll();
	        logger.info("**** Finished TC_001_RegisterUserTest ****");
	        //delete this log if we are going to enable verifyAccountDeleted method
		}
	}
			
		/*@Test(priority=3)
		public void verifyAccountDeleted() {
		    SoftAssert softAssert = new SoftAssert();
		    HomePage hp = new HomePage(driver);
            AccountCreated_DeletedPage ac = new AccountCreated_DeletedPage(driver);
			try {
				logger.info("***** Starting TC_001_RegisterUserTest: verifyAccountDeleted *****");
				
				//17. Click 'Delete Account' button
				hp.clickDeleteAccount();
				
				//18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
				String accountDeletedTxt = ac.VerifyAccountCreated_Deleted();
				softAssert.assertEquals(accountDeletedTxt,"ACCOUNT DELETED!","Account Deletion Unsuccessful/Failed");
				
				ac.clickContinue();
				
				logger.info("Account Deleted Successfully");
		
				
		        
			}catch(Exception e) {
				logger.error("Test Failed: "+e.getMessage());
				//softAssert.fail("Test Failed: "+e.getMessage());
			}
			finally {
				// Collect all the assertion results which are present in each @Test method
		        softAssert.assertAll();   
				logger.info("**** Finished TC_001_RegisterUserTest ****");
			}
	}*/
}


