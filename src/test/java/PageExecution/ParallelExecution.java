package PageExecution;

import java.io.IOException;

import org.testng.annotations.Test;

import BaseClassPackage.BaseClass;
import PageFactory.RegLocators;

public class ParallelExecution extends BaseClass{

	@Test
	public void testcase1() throws IOException, InterruptedException {
		try {
			BaseClass.browserSelect("firefox");
			BaseClass.loadUrl(BaseClass.readProperty("Url"));
			Thread.sleep(1000);
			RegLocators rl = new RegLocators();
			BaseClass.getWebElementByXpath(rl.signUp).click();
			BaseClass.getWebElementByXpath(rl.fullName).sendKeys(BaseClass.readProperty("wrongFullName"));
			Thread.sleep(1000);
			System.out.println("Testcase1 - To Open SignUp and Give Invalid Fullname");
			BaseClass.BrowserClose();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	@Test(groups = {"smoke"})
	public void testcase2() throws IOException, InterruptedException{
		try {
			BaseClass.browserSelect("chrome");
			BaseClass.loadUrl(BaseClass.readProperty("Url"));
			RegLocators rl = new RegLocators();
			BaseClass.getWebElementByXpath(rl.signUp).click();
			Thread.sleep(500);
			BaseClass.getWebElementByXpath(rl.fullName).sendKeys(BaseClass.readProperty("wrongFullName"));
			Thread.sleep(500);
			System.out.println("Testcase2 - To Open SignUp and Give Invalid Fullname");
			BaseClass.BrowserClose();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

}
