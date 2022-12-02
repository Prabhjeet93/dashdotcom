package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import mainUtils.UtilityClass;

public class Dynamicdata {
	
	WebDriver driver;
	
	By txthead_dynamic_content =By.xpath("//*[@id=\"content\"]/div/h3");
	By txt_dynamic_content =By.xpath("//*[@id=\"content\"]/div[3]/div[2]");
	
	
	By txthead_dynamic_control =By.xpath("//*[@id=\"content\"]/div[1]/h4[1]");
	By btn_Remove =By.xpath("//*[@id=\"checkbox-example\"]/button");
	By btn_Add =By.xpath("//*[@id=\"checkbox-example\"]/button[text()='Add']");
	
	By clk_checkbox =By.xpath("//*[@id=\"checkbox\"]/input");
	By click_checkbox =By.xpath("//*[@id=\"checkbox\"]");
	
	By btn_enable =By.xpath("//*[@id=\"input-example\"]/button[text()=\"Enable\"]");
	By btn_disable =By.xpath("//*[@id=\"input-example\"]/button[text()=\"Disable\"]");
	By enter_text =By.xpath("//*[@id=\"input-example\"]/input");
	
	By txthead_dynamic_loading =By.xpath("//*[@id=\"content\"]/div/h3");
	By btn_start =By.xpath("//*[@id=\"start\"]/button");
	By verify_dynamic_loading =By.id("finish");
	
    

    public Dynamicdata(WebDriver driver){

    	
        this.driver = driver;

    }
    UtilityClass utlity = new UtilityClass(driver);

    public void dynamic_content(String toptext) {
    	
    	System.out.println("Inside Dynamic Content Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    	
    	
    	utlity.validate_Text(txthead_dynamic_content,toptext,driver);
    	
    	WebElement ele = driver.findElement(txt_dynamic_content);
    	String initial_txt = ele.getText();
    	driver.navigate().refresh();
    	
    	WebDriverWait wait = new WebDriverWait(driver,30);
		//wait.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(txt_dynamic_content)));

    	wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(ele)));
		//wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf("table")));
		
    	//utlity.explicit_wait(txt_dynamic_content, driver);
    	//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ele);
    	int count=0;
    	String after_txt=null;
    	while(count<=10) {
    		try {
    			after_txt = ele.getText();
    			System.out.println(after_txt);
    			
    			break;
    		}
    		catch(StaleElementReferenceException exc) {
    			exc.printStackTrace();
    			
    		}
    		count++;
    	}
    	
    	System.out.println(initial_txt);
    	System.out.println(after_txt);
    	
    	Assert.assertNotEquals(initial_txt, after_txt, "Text is not matching");
    	
    }
    
    public void dynamic_Control(String toptext) throws InterruptedException {
    	
    	System.out.println("Inside Dynamic Control Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    	
    	String actual = driver.findElement(txthead_dynamic_control).getText();
    	Assert.assertEquals(actual, toptext);
    	
    	
//		Test clicks on the Remove Button and uses explicit wait.
    	WebElement btn_rmv = driver.findElement(btn_Remove);
    	btn_rmv.click();
    	utlity.explicit_wait(btn_Add,driver);    	
    	
//		Test asserts that the checkbox is gone.
    	WebElement clk_chkbx=null;
    	try {
    		clk_chkbx = driver.findElement(clk_checkbox);
    	}
    	catch (Exception e) {
    		
    		System.out.println("Checkbox is gone");
    	}
    	//Assert.assertEquals(clk_chkbx.isDisplayed(), false);
    	
    	
//		Test clicks on Add Button and uses explicit wait.
    	WebElement button_Add = driver.findElement(btn_Add);
    	button_Add.click();
    	utlity.explicit_wait(btn_Remove,driver);
    	
//		Test asserts that the checkbox is present.
    	Thread.sleep(5000);
    	WebElement click_chkbx = driver.findElement(click_checkbox);
    	Assert.assertEquals(click_chkbx.isDisplayed(), true);
    	
    	
//		Test clicks on the Enable Button and uses explicit wait.
    	WebElement button_enable = driver.findElement(btn_enable);
    	button_enable.click();
    	utlity.explicit_wait(btn_disable,driver);
    	
//		Test asserts that the text box is enabled.
    	WebElement enabled_enter_text = driver.findElement(enter_text);
    	Assert.assertEquals(enabled_enter_text.isEnabled(), true);
    	enabled_enter_text.sendKeys("test");
    	
//		Test clicks on the Disable Button and uses explicit wait.
    	WebElement button_disable = driver.findElement(btn_disable);
    	button_enable.click();
    	utlity.explicit_wait(btn_enable,driver);
    	
//		Test asserts that the text box is disabled.
    	Assert.assertEquals(enabled_enter_text.isEnabled(), false);
    	
    }
    
    public void dynamic_loading(String toptext) throws InterruptedException {
    	
    	System.out.println("Inside Dynamic loading Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    	
    	
    	utlity.validate_Text(txthead_dynamic_loading,toptext,driver);

//    	WebElement button_start = driver.findElement(btn_start);
//    	button_start.click();
    	utlity.click_anything(btn_start,driver);
    	utlity.explicit_wait(verify_dynamic_loading,driver);    	
    	
//    	WebElement txt_verify_dynamic_loading = driver.findElement(verify_dynamic_loading);
//    	
//    	Assert.assertEquals(txt_verify_dynamic_loading.getText(), "Hello World!");
    	
    	utlity.validate_Text(verify_dynamic_loading,"Hello World!",driver);
    	
    }
}
