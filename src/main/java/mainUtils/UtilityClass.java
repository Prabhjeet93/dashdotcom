package mainUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import dashtest.MainScripts;


public class UtilityClass {
	WebDriver driver;
	
	public UtilityClass(WebDriver driver){
	        this.driver = driver;
	    }
	//UtilityClass utility = new UtilityClass(driver);
	
	public void validate_Text(By wb, String expected, WebDriver driver) {
		this.explicit_wait(wb,driver);
		WebElement val_txt = driver.findElement(wb);
		String actual = val_txt.getText().trim();
		System.out.println("validating this text: "+actual);
		Assert.assertEquals(actual, expected);
		
	}
	public void click_anything(By wb, WebDriver driver) {
		this.explicit_wait(wb,driver);
		WebElement clk = driver.findElement(wb);
		
		clk.click();
	}
	public void clear_txt_box(By wb, WebDriver driver) {
		this.explicit_wait(wb,driver);
		WebElement clk = driver.findElement(wb);
		
		clk.clear();
	}
	public void enter_text(By wb, WebDriver driver, String txt) {
		this.explicit_wait(wb,driver);
		WebElement clk = driver.findElement(wb);
		
		clk.sendKeys(txt);
	}
	
	public void dropdown(By wb, String selecttype, String text, WebDriver driver) {
		this.explicit_wait(wb,driver);
		WebElement val_drpdown = driver.findElement(wb);
		
		Select sl = new Select(val_drpdown);
		if (selecttype == "visibletext") {
			sl.selectByVisibleText(text);
		}
		else if (selecttype == "value") {
			sl.selectByValue(text);
		}
		else if (selecttype == "index") {
			sl.selectByIndex(Integer.valueOf(text));
		}
		WebElement option = sl.getFirstSelectedOption();
		String SelectedText = option.getText();
		System.out.println("#################: "+SelectedText);
	}
	public void val_dropdown_value(By wb, String text, WebDriver driver) {
		this.explicit_wait(wb,driver);
		WebElement val_drpdown = driver.findElement(wb);
		
		Select sl = new Select(val_drpdown);
		WebElement option = sl.getFirstSelectedOption();
		String SelectedText = option.getText();
		System.out.println("#################: "+SelectedText);
		Assert.assertEquals(SelectedText, text);
	}
	
	public void assert_dropdownvalue(By wb, String text, WebDriver driver ) {
		this.explicit_wait(wb,driver);
		WebElement val_drpdown = driver.findElement(wb);
		
		Assert.assertEquals(val_drpdown.getText(), text);
	}
	
	public void click_checkbox(By wb, WebDriver driver) {
		//first check it is not checked.
		this.explicit_wait(wb,driver);
		WebElement val_chkbox = driver.findElement(wb);
		
		Assert.assertEquals(val_chkbox.isSelected(), false);
		if (!val_chkbox.isSelected()) {
			val_chkbox.click();
		}
	}
	
	public void unclick_checkbox(By wb, WebDriver driver) {
		//first check it is not checked.
		this.explicit_wait(wb,driver);
		WebElement val_chkbox = driver.findElement(wb);
		
		Assert.assertEquals(val_chkbox.isSelected(), true);
		if (val_chkbox.isSelected()) {
			val_chkbox.click();
		}
	}
	
	public void right_click(By wb, WebDriver driver) {
		System.out.println(driver);
		Actions actions = new Actions(driver);
		this.explicit_wait(wb,driver);
		WebElement contextmenu = driver.findElement(wb);
		
		actions.moveToElement(contextmenu).contextClick().build().perform();
	}
	
	public void chk_isSelected(By wb, boolean flag, WebDriver driver) {
		//first check it is not checked.
		this.explicit_wait(wb,driver);
		WebElement sel_chkbox = driver.findElement(wb);
		
		Assert.assertEquals(sel_chkbox.isSelected(), flag);
	}
	
	public void chk_isEnabled(By wb, boolean flag, WebDriver driver) {
		//first check it is not checked.
		this.explicit_wait(wb,driver);
		WebElement sel_chkbox = driver.findElement(wb);
		Assert.assertEquals(sel_chkbox.isEnabled(), flag);
	}

	public void validate_alert_msg(String expected,WebDriver driver) {
		String actual = driver.switchTo().alert().getText();
		Assert.assertEquals(actual, expected);
	}
	
	public void accept_alert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	public void enter_txt_jsAlerts(WebDriver driver, String txt) {
		driver.switchTo().alert().sendKeys(txt);
	}
	
	public void dragdrop(WebDriver driver, By sourceLocator, By destinationLocator) {
		
		this.explicit_wait(sourceLocator,driver);
		WebElement sourceElement = driver.findElement(sourceLocator);
    	WebElement destinationElement = driver.findElement(destinationLocator);
		try {
	        if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
	            //Actions action = new Actions(driver);
	            //action.dragAndDrop(sourceElement, destinationElement).build().perform();
	            //action.clickAndHold(sourceElement).moveToElement(destinationElement).release().perform();
	            int x = sourceElement.getLocation().x;
	            int y = destinationElement.getLocation().y;

	    
	            JavascriptExecutor js = (JavascriptExecutor) driver;
	            js.executeScript("function createEvent(typeOfEvent) {\n" +"var event =document.createEvent(\"CustomEvent\");\n" +"event.initCustomEvent(typeOfEvent,true, true, null);\n" +"event.dataTransfer = {\n" +"data: {},\n" +"setData: function (key, value) {\n" +"this.data[key] = value;\n" +"},\n" +"getData: function (key) {\n" +"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n" +"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n" +"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" +"}\n" +"if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" +"} else if (element.fireEvent) {\n" +"element.fireEvent(\"on\" + event.type, event);\n" +"}\n" +"}\n" +"\n" +"function simulateHTML5DragAndDrop(element, destination) {\n" +"var dragStartEvent =createEvent('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n" +"var dropEvent = createEvent('drop');\n" +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" +"var dragEndEvent = createEvent('dragend');\n" +"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" +"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n" +"simulateHTML5DragAndDrop(source,destination);",sourceElement, destinationElement);

	            System.out.println("drag and drop done");
	        } else {
	            System.out.println("Element was not displayed to drag");
	        }
	    } catch (StaleElementReferenceException e) {
	        System.out.println("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
	                + e.getStackTrace());
	    } catch (NoSuchElementException e) {
	        System.out.println("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "+ e.getStackTrace());
	    } catch (Exception e) {
	        System.out.println("Error occurred while performing drag and drop operation "+ e.getStackTrace());
	    }
	}

	
	public void scrolldown(WebDriver driver) {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
        //Scroll down till the bottom of the page
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	public void mouse_hover(WebDriver driver, By wb) {
		
		Actions action = new Actions(driver);
		this.explicit_wait(wb,driver);
		WebElement ele = driver.findElement(wb);
		

		//Performing the mouse hover action on the target element.
		action.moveToElement(ele).perform();
	}
	public void assert_text(By wb, String text, WebDriver driver) {
		this.explicit_wait(wb,driver);
		WebElement ele = driver.findElement(wb);
		
		Assert.assertEquals(ele.getText(), text);
	}

	public static String configReader(String name) {
		Properties prop = new Properties();
		String config_value=null;
		 //defining the project path
		String projectPath = System.getProperty("user.dir");
		try {
			//create a object for class InputStream
			   InputStream input = new FileInputStream(projectPath + "\\config.properties");
			   //Load properties file
			   System.out.println(projectPath+"\\config.properties");
			   prop.load(input);
			   //get values from properties file
			   System.out.println(name);
			   config_value = prop.getProperty(name);
			   System.out.println(config_value);
			} catch (Exception exp) {
			System.out.println(exp.getMessage());
			   System.out.println(exp.getCause());
			   exp.printStackTrace();
			}
		return config_value;
	
	
	}
	public void explicit_wait(By ele, WebDriver driver) {
		System.out.println("Inside explicit wait: "+driver);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
	}
	
	@SuppressWarnings("deprecation")
	public void fluent_wait(String element, WebDriver driver) {
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(5000, TimeUnit.MILLISECONDS);
		wait.pollingEvery(250, TimeUnit.MILLISECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.alertIsPresent());


		
		}

}
