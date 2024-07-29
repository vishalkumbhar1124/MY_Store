package com.mystore.BasePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.BeforeTest;

public class BasesClass {
	public static Properties prop;
	public static WebDriver driver;
	@BeforeTest
	public void loadconfig() {
		try {
			prop = new Properties();
			System.out.println("super constructor invoked");
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+"\\Confugration\\config.properties");
			prop.load(ip);
			System.out.println("driver: "+ driver);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void LaunchApp() {
		WebDriverManager.chromedriver().setup();
		String browserName = prop.getProperty("browser");
		
		if(browserName.contains("Chrome")) {
			driver = new ChromeDriver();
			}else if (browserName.contains("FireFox")){
				driver = new FirefoxDriver();
	}else if (browserName.contains("IE")){
		driver = new InternetExplorerDriver();
}

}
}
