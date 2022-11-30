package dashtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import mainUtils.UtilityClass;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

@Test
public class MainScripts {
	WebDriver driver=null;

	@BeforeMethod
	public void beforeMethod() {
		ChromeOptions chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(chromeOptions);
		driver.manage().window().maximize();

	}
	@Test
	public void loginSuccess() throws InterruptedException {
		System.out.println("Test Java");
		System.out.println("Navigating to the page");
		driver.get("http://localhost:7080/login");
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		Assert.assertEquals(pageTitle, "The Internet");
		
		System.out.println(UtilityClass.configReader("userid"));
		Thread.sleep(5000);
		
		
		
	}
//	@Test
//	public void loginFailed() throws InterruptedException {
//		System.out.println("Test Java");
//		System.out.println("Navigating to the page");
//		driver.get("http://localhost:7080/login");
//		String pageTitle = driver.getTitle();
//		System.out.println(pageTitle);
//		Assert.assertEquals(pageTitle, "The Internet");
//		Thread.sleep(5000);
//	}
//	@Test
//	public void checkbox() throws InterruptedException {
//		System.out.println("Test Java");
//		System.out.println("Navigating to the page");
//		driver.get("http://localhost:7080/checkboxes");
//		String pageTitle = driver.getTitle();
//		System.out.println(pageTitle);
//		Assert.assertEquals(pageTitle, "The Internet");
//		Thread.sleep(5000);
//	}

//	@AfterMethod
//	public void afterMethod() {
//		driver.quit();
//	}

}
