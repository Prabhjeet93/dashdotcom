package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import mainUtils.UtilityClass;

public class login {
	
	WebDriver driver;
	
	By txt_login_page = By.xpath("//*[@id=\"content\"]/div/h2");
	By usrname = By.xpath("//*[@id=\"username\"]");  
	
    By passwd = By.id("password");
    By titleText =By.className("example");
    By login = By.className("radius");
    By success_msg = By.xpath("//*[@id=\"flash\"]");
    By btn_logout = By.xpath("//*[@href=\"/logout\"]/i");

    public login(WebDriver driver){

        this.driver = driver;

    }
    UtilityClass utlity = new UtilityClass(driver);
    
    public void enterCredentials(String username, String password, String toptext){
    	
    	System.out.println("Inside login Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    	
    	utlity.validate_Text(txt_login_page,toptext,driver);
    	
        //driver.findElement(usrname).sendKeys(username);
        utlity.enter_text(usrname, driver,username);
        //driver.findElement(passwd).sendKeys(password);
        utlity.enter_text(passwd, driver,password);
        //driver.findElement(login).click();
        utlity.click_anything(login, driver);

    }
    
    public void validate_login(String message) throws InterruptedException {
    	//utlity.fluent_wait("success_msg");
//    	Thread.sleep(10000);
//    	String actual_msg = driver.findElement(success_msg).getText();
//    	System.out.println(actual_msg);
    	
    	utlity.validate_Text(success_msg,message,driver);
    	
    	//Assert.assertEquals(actual_msg.trim(), message);
    	//Boolean btn_value = driver.findElement(btn_logout).isEnabled();
    	if(message.contains("invalid")) {
    		utlity.chk_isEnabled(login, true, driver);
    	}
    	else {
    		utlity.chk_isEnabled(btn_logout, true, driver);
    	}
    }

}
