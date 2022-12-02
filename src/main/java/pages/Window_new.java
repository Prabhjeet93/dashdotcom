package pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import mainUtils.UtilityClass;

public class Window_new {
	
	WebDriver driver;
	
	
	By txt_filedownload =By.xpath("//*[@id=\"content\"]/div/h3");
	By link_clik_msg = By.linkText("Click here");
	By vali_msg = By.id("flash");

	
	 public Window_new(WebDriver driver){
		 this.driver = driver;

	    }
	 UtilityClass utlity = new UtilityClass(driver);
	 
	 public void navigate_new_window(String toptext) {
		 System.out.println("Inside navigate_new_window Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	    	
		 String actual = driver.findElement(txt_filedownload).getText();
		 Assert.assertEquals(actual, toptext);
	    
		 utlity.scrolldown(driver);
		 
		 //WebElement floatmenu = driver.findElement(By.id("menu")).isDisplayed();
		 
		 WebElement clk_new_window = driver.findElement(By.xpath("//*[@id=\"content\"]/div/a"));
		 clk_new_window.click();
		 
		 String mainWindowHandle = driver.getWindowHandle();
		 Set<String> windows = driver.getWindowHandles(); 
		 System.out.println(windows);
		 Iterator<String> iterator = windows.iterator();
		 while (iterator.hasNext()) {
	            String ChildWindow = iterator.next();
	                if (!mainWindowHandle.equalsIgnoreCase(ChildWindow)) {
	                driver.switchTo().window(ChildWindow);
	       		 	Assert.assertEquals(driver.getTitle(), "New Window");
	       		 	WebElement txt_new_window = driver.findElement(By.className("example"));
	       		 Assert.assertEquals(txt_new_window.getText(), "New Window");
	                }
	        }
	 }
	 
	 public void notification_message(String toptext) throws InterruptedException {
		 System.out.println("Inside notification_message Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	    	
		 String actual = driver.findElement(txt_filedownload).getText();
		 Assert.assertEquals(actual, toptext);
	    
		 
		 int counter=0;
		 String success = "Action successful\r\n" + 
		 		"×";
		 String unsuccess="Action unsuccesful, please try again\r\n" + 
		 		"×";
		 while(counter<=2) {
			 System.out.println("Looping time");
//				Test clicks on the Click Here link a multiple times.
			 WebElement clk_notification1 = driver.findElement(link_clik_msg);
			 clk_notification1.click();
			 
//				Test asserts that one of the “Action Successful”, “Action unsuccessful, please try again” and “Action Unsuccessful” messages show on click.
			 WebElement verify_msg1 = driver.findElement(vali_msg);
			 
//			 utlity.validate_Text(verify_msg1,success);
			 utlity.explicit_wait(link_clik_msg,driver);
			 WebElement clk_notification2 = driver.findElement(link_clik_msg);
			 clk_notification2.click();
			 WebElement verify_msg2 = driver.findElement(vali_msg);
			 //utlity.validate_Text(verify_msg2,unsuccess);
			 
			 WebElement clk_notification3 = driver.findElement(link_clik_msg);
			 clk_notification3.click();
			 WebElement verify_msg3 = driver.findElement(vali_msg);
			 //utlity.validate_Text(verify_msg3,unsuccess);
//				
			counter++; 
		 }
	 	}

}
