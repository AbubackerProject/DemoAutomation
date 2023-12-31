package BaseClassPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static void browserSelect(String option) {
		switch (option) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			System.out.println("Chrome Browser is Launched");
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			driver.manage().window().maximize();
			System.out.println("Edge Browser is Launched");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			System.out.println("FireFox Browser is Launched");
			break;
		}
	}
	public static void loadUrl(String url){
		driver.navigate().to(url);
	}
	public static void implicitWait(int no){
		driver.manage().timeouts().implicitlyWait(no, TimeUnit.SECONDS);
	}
	public static String getUrl() {
		return driver.getCurrentUrl();
	}
	public static WebElement getWebElementById(String id) {
		return driver.findElement(By.id(id));
	}
	public static WebElement getWebElementByXpath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	public static WebElement getWebElementByCSS(String selector) {
		return driver.findElement(By.cssSelector(selector));
	}
	public static String path = System.getProperty("user.dir");

	public static String readProperty(String Key) throws IOException {
		File file = new File(path+"/Credential.properties/");
		FileInputStream fis = new FileInputStream(file);
		Properties po = new Properties();
		po.load(fis);
		String value = po.getProperty(Key);
		return value;
	}
	public static void closeWindow(){
		driver.close();
	}
	public static void BrowserClose(){
		driver.quit();
		System.out.println("Browser closed");
	}
}
