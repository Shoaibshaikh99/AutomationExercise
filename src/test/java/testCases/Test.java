package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) {
		
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://automationexercise.com/");
		
		driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();
		driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("abc@gmail.com");
		driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("Test@123");
		driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
		/*driver.findElement(By.xpath(""));
		driver.findElement(By.xpath(""));*/
		

	}

}
