package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import mainUtils.UtilityClass;

public class Drag_and_Drop {
	
	WebDriver driver;
	

	By sourceLocator1 = By.id("column-a");
	By destinationLocator1 = By.id("column-a");
	By txt_dragdrop =By.xpath("//*[@id=\"content\"]/div/h3");
    

    public Drag_and_Drop(WebDriver driver){

        this.driver = driver;

    }
    UtilityClass utlity = new UtilityClass(driver);
	
    public void drag_n_drop(String toptext) throws InterruptedException{
    	
    	System.out.println("Inside drag_n_drop Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
    	
    	utlity.validate_Text(txt_dragdrop,toptext,driver);
    	
    	utlity.dragdrop(driver,sourceLocator1,destinationLocator1);
    	utlity.validate_Text(sourceLocator1, "B",driver); // validating text of source
    	utlity.validate_Text(destinationLocator1, "A",driver); // validating text of source
    	
    	

    }

}
