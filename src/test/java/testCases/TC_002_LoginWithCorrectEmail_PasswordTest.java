package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.Signup_login_Page;
import testBase.BaseClass;
/*1. Launch browser
2. Navigate to url 'http://automationexercise.com'
3. Verify that home page is visible successfully
4. Click on 'Signup / Login' button
5. Verify 'Login to your account' is visible
6. Enter correct email address and password
7. Click 'login' button
8. Verify that 'Logged in as username' is visible
9. Click 'Delete Account' button
10. Verify that 'ACCOUNT DELETED!' is visible*/
public class TC_002_LoginWithCorrectEmail_PasswordTest extends BaseClass{
	
	//creating object variables to invoke pageObject classes
	/*HomePage hp = new HomePage(driver);
	Signup_login_Page slp = new Signup_login_Page(driver);
	AccountCreated_DeletedPage ac = new AccountCreated_DeletedPage(driver);*/
	
	@Test(priority=1)
	public void verifyHomePageIsVisible1() {
		SoftAssert softAssert = new SoftAssert();
		HomePage hp = new HomePage(driver);
		Signup_login_Page slp = new Signup_login_Page(driver);
		logger.info("*** Starting TC_002_LoginWithCorrectEmail_Password - verifyHomePageIsVisible() ***");
		try {
		
		//3. Verify that home page is visible successfully
		boolean hpStatus = hp.isHomePageDisplayed();
		softAssert.assertEquals(hpStatus,true,"Home Page is not displayed");
		logger.info("Home page is displayed successfully.");
		
		//4. Click on 'Signup / Login' button
		hp.clickSignup_Login();
		
		//5. Verify 'Login to your account' is visible
		String loginText = slp.getLoginMsg();
		softAssert.assertEquals(loginText,"Login to your account","Login Text Message Mismatch");
		logger.info("Successfully Navigated To Login Page.");
		
		
		/*TC_001_RegisterUserTest tc1 =new TC_001_RegisterUserTest();
		tc1.verifyHomePageIsVisible();*/
		}catch(Exception e) {
			logger.error("Test Failed: "+e.getMessage());
			//softAssert.fail("Test Failed: "+e.getMessage());
		}
		finally {
			softAssert.assertAll();
		}
	}
		
		@Test(priority=2)
		public  void verifyLoginIsSuccessfull() {
			/*I have made email and password variable as public in TC_001 so that i can use
			  those generated email and password in this TC to delete it. And delete method which
			  is in TC_001 commented, if that method is enable then i can't login with those login
			  details */
			
			/*We need to create object for test case 1 from where we are going to access two public
			  variables email and password.Using created object variable tc1.variablename we can access
			  value of those both variables*/
			 SoftAssert softAssert = new SoftAssert();
			HomePage hp = new HomePage(driver);
			Signup_login_Page slp = new Signup_login_Page(driver);
			 try {
				logger.info("*** Starting TC_002_LoginWithCorrectEmail_Password - verifyLoginIsSuccessfull()");
			
			//6. Enter correct email address and password
		    String email = TC_001_RegisterUserTest.email;
		    String password = TC_001_RegisterUserTest.password;
			//TC_001_RegisterUserTest tc1 = new TC_001_RegisterUserTest();
			slp.fillLoginDetails(email,password);
			
			//7. Click 'login' button
			slp.clickLogin();
			
			//8. Verify that 'Logged in as username' is visible
			boolean loginStatus = hp.verifyLoginSuccess();
			softAssert.assertEquals(loginStatus,true,"Login unsuccessful or invalid login credentials");
			
			logger.info("Successfully Login into the application");
			
		}catch(Exception e) {
			logger.error("Test Failed: "+e.getMessage());
			//softAssert.fail("Test Failed: "+e.getMessage());
		}
			finally {
				softAssert.assertAll();
			}
		}
		
		/*@Test(priority=3)
		public void verifyAccountDeleted() {
			SoftAssert softAssert = new SoftAssert();
			HomePage hp = new HomePage(driver);
			AccountCreated_DeletedPage ac = new AccountCreated_DeletedPage(driver);
			try {
				logger.info("***** Starting TC_002_LoginWithCorrectEmail_Password  verifyAccountDeleted *****");
				
				//9. Click 'Delete Account' button
				hp.clickDeleteAccount();
				
				//10. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
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
