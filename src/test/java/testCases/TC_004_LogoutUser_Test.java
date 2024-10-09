package testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import pageObjects.HomePage;
import pageObjects.Signup_login_Page;
import testBase.BaseClass;

public class TC_004_LogoutUser_Test extends BaseClass{
	  /*1. Launch browser
	2. Navigate to url 'http://automationexercise.com'
	3. Verify that home page is visible successfully
	4. Click on 'Signup / Login' button
	5. Verify 'Login to your account' is visible
	6. Enter correct email address and password
	7. Click 'login' button
	8. Verify that 'Logged in as username' is visible
	9. Click 'Logout' button
	10. Verify that user is navigated to login page*/
	
	    @Test(priority=1)
	    public void LoginToAccount() {
	    	 SoftAssert softAssert = new SoftAssert();
	    	 HomePage hp = new HomePage(driver);
			 Signup_login_Page slp = new Signup_login_Page(driver);
	    	logger.info("*** TC_004_LogoutUser_Test - Started ***");
	    	try {
	    		
	    	//3. Verify that home page is visible successfully
	    	boolean hpStatus = hp.isHomePageDisplayed();
	    	softAssert.assertEquals(hpStatus,true,"Home Page is not displayed");
	    	logger.info("Home Page displayed successfully");
	    	
	    	//4. Click on 'Signup / Login' button
	    	hp.clickSignup_Login();
	    	
	    	//5. Verify 'Login to your account' is visible
	    	String loginText = slp.getLoginMsg();
	    	softAssert.assertEquals(loginText,"Login to your account","Login Text Mismatch/not displayed");
	    	logger.info("Login Text Verified");
	    	
	    	//6. Enter correct email address and password
	    	String email = TC_001_RegisterUserTest.email;
	    	String password = TC_001_RegisterUserTest.password;
	    	
	    	slp.fillLoginDetails(email, password);
	    	
	    	//7. Click 'login' button
	    	slp.clickLogin();
	    	
	    	//8. Verify that 'Logged in as username' is visible
    		boolean loginStatus = hp.verifyLoginSuccess();
    		softAssert.assertEquals(loginStatus,true,"Login Unsuccessful");
    		logger.info("Login Successfully");

	    	}catch(Exception e) {
	    		logger.error("Test Failed: "+e.getMessage());
	    	}finally {
	    		softAssert.assertAll();
	    	}
	    }
	    
	    @Test(priority=2)
	    public void LogoutUser() {
	    		SoftAssert softAssert = new SoftAssert();
	    		HomePage hp = new HomePage(driver);
	    	try {

	    		//9. Click 'Logout' button
	    		hp.clickLogout();
	    		
	    		//10. Verify that user is navigated to login page
	    		boolean logoutStatus = hp.isSignupBtnClicked();
	    		softAssert.assertEquals(logoutStatus,true,"Logout Unsuccessful");
	    		logger.info("Logout Successful");
	    		
	    	}catch(Exception e) {
	    		logger.error("Test Failed"+e.getMessage());
	    	}finally {
	    		softAssert.assertAll();
	    	}
	    }
}
