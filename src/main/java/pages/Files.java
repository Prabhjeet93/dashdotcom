package pages;



import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.File;
import java.io.FileFilter;

import mainUtils.UtilityClass;

public class Files {
	WebDriver driver;
	private static String downloadPath = "C:\\Users\\HP\\Downloads";
	
	By txt_filedownload =By.xpath("//*[@id=\"content\"]/div/h3");
	By lnk_dwnlod_file =By.linkText("some-file.txt");
	
	By chooseFile = By.id("file-upload");
	By btn_file_submit= By.id("file-submit");
	
	 public Files(WebDriver driver){

	    	this.driver = driver;

	    }
	 UtilityClass utlity = new UtilityClass(driver);
	 
	 public void file_Download(String toptext) throws InterruptedException {
//	    	
//	    	System.out.println("Inside File Download Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//	    	
//	    	String actual = driver.findElement(txt_filedownload).getText();
//	    	Assert.assertEquals(actual, toptext);
//	    	
//	    	WebElement link_download_file = driver.findElement(lnk_dwnlod_file);
//	    	link_download_file.click();
//	    	Thread.sleep(5000);
//	    	File dir = new File(downloadPath);
//	        File[] files = dir.listFiles();
//	    	
//		    //File getLatestFile = getLatestFilefromDir(downloadPath);
//	        bool result = CheckFile(link_download_file.getText());
//	        File getLatestFile = files[0];
//		    String fileName = getLatestFile.getName();
//		    System.out.println(fileName);
//		    //Assert.assertTrue(fileName.equals("some-file.txt"), "Downloaded file name is not matching with expected file name");
//		    //Assert.assertTrue(isFileDownloaded(downloadPath, "mailmerge.xls"), "Failed to download Expected document");
//		    
//		    Assert.assertTrue(fileName.equals("some-file.txt"), "Downloaded file name is not matching with expected file name");
//	    	
	    }
	 
	 public void file_Upload(String toptext) throws InterruptedException {
		 System.out.println("Inside File upload Method $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
	    	
		 
		 utlity.validate_Text(txt_filedownload,toptext,driver);

		 String path = "C:\\Users\\HP\\Downloads\\some-file.txt";
		 
		 utlity.enter_text(chooseFile, driver,path);
		 utlity.click_anything(btn_file_submit, driver);
//		 driver.findElement(By.id("file-submit")).click();
//		 System.out.println(driver.findElement(By.id("uploaded-files")).getText());
		 
//		 Assert.assertEquals(driver.findElement(By.id("uploaded-files")).getText(), "some-file.txt");
		 utlity.validate_Text(btn_file_submit,"some-file.txt",driver);
	 }
}
