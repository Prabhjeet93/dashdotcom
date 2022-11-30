package mainUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class UtilityClass {
	
	public void validate_Text() {
		
	}
	public void dropdown() {
		
	}
	
	public void click_checkbox() {
		
	}
	public void fileupload_download() {
		
	}
	public void iframes() {
	
	}
	public void javascripAlerts() {
	
	}
	public void notification_message() {
	
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

}
