package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.Signup_login_Page;
import testBase.BaseClass;

public class TC_003_Login_User_with_incorrect_email_and_passwordTest extends BaseClass{
	
	//creating object variables to invoke pageObject classes
	/*HomePage hp = new HomePage(driver);
	Signup_login_Page slp = new Signup_login_Page(driver);*/
	
	
	@Test(priority =1)
	public void verifyHomePageIsVisible2() {
		 SoftAssert softAssert = new SoftAssert();
		 HomePage hp = new HomePage(driver);
		 Signup_login_Page slp = new Signup_login_Page(driver);
		 logger.info("***  TC_003_Login_User_with_incorrect_email_and_password - Started successfully ");
		 
		try {
			
			//3. Verify that home page is visible successfully
			boolean hpStatus = hp.isHomePageDisplayed();
			softAssert.assertEquals(hpStatus, true,"Homepage is not visible");
			logger.info("Home Page is displayed successfully");
			
			//4. Click on 'Signup / Login' button
			hp.clickSignup_Login();
			
			//5. Verify 'Login to your account' is visible
			String loginTxt = slp.getLoginMsg();
			softAssert.assertEquals(loginTxt,"Login to your account"," Login Text mismatch");
			logger.info("Succefully verified login text");
			
		}catch(Exception e){
			logger.error("Test Failed: "+e.getMessage());
			//softAssert.fail("Test Failed: "+e.getMessage());
		}
		finally {
			softAssert.assertAll();
		}
	}
		
		@Test(priority=2)
		public void VerifyNegativeLoginScenario() {
		   SoftAssert softAssert = new SoftAssert();
		   Signup_login_Page slp = new Signup_login_Page(driver);
			
		   try {
			
			//6. Enter incorrect email address and password
			String email = randomString()+"@gmail.com";
			String password = "A"+randomAlphaNumeric();
			
			slp.fillLoginDetails(email, password);
			
			//7. Click 'login' button
			slp.clickLogin();
			
			//8. Verify error 'Your email or password is incorrect!' is visible
			String errorText = slp.getLoginErrorMessage();
			
			softAssert.assertEquals(errorText,"Your email or password is incorrect!",
					"Unable read error message or inccorect error message");
			logger.info("Error message occurred successfully for incorrect username and password");
			
			
			
		}catch(Exception e) {
			logger.error("Test Failed: "+e.getMessage());
			//softAssert.fail("Test Failed: "+e.getMessage());
		}finally {
			softAssert.assertAll();
		}
	}

}
