package PageExecution;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import BaseClassPackage.BaseClass;
import BaseClassPackage.Utility;
import PageFactory.RegLocators;
import io.qameta.allure.Allure;

public class SignUpExecution extends Utility{

	@BeforeMethod
	public static void beforeMethod() throws InterruptedException, IOException  {
		BaseClass.browserSelect(BaseClass.readProperty("BrowserType"));
		BaseClass.loadUrl(BaseClass.readProperty("Url"));
		Thread.sleep(1000);
		System.out.println("Before method");
	}
	
	@AfterMethod
	public static synchronized void updatedStatus(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.SUCCESS) {
			File  screenShot = Utility.captureScreenshot(driver, result.getName());
			Allure.addAttachment("Screenshot", FileUtils.openInputStream(screenShot));
			BaseClass.BrowserClose();
			System.out.println("After method");
		}
	}

	@DataProvider(name = "testData")
	public Object[][] testData() {
		return new Object[][] {
			{"Abu", "abu@gmail.com"},  // Test data set 1
		};
	}

	@Test
	public void testcase1() throws IOException{
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.fullName).sendKeys(BaseClass.readProperty("wrongFullName"));
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase1 - Passed ");
		}
		else {
			System.out.println("Testcase1 - Failed");
		}
	}
	@Test
	public void testcase2() throws IOException, InterruptedException, AWTException{
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.fullName).sendKeys(BaseClass.readProperty("validFullName"));
		BaseClass.getWebElementByXpath(rl.checkBox).click();
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase2 - Passed ");
		}
		else {
			System.out.println("Testcase2 - Failed");
		}
	}
	@Test(enabled=false)
	public void testcase3() throws IOException{
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.emaiId).sendKeys(BaseClass.readProperty("wrongEmail"));
		BaseClass.getWebElementByXpath(rl.checkBox).click();
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase3 - Passed ");
		}
		else {
			System.out.println("Testcase3 - Failed");
		}
	}
	@Test
	public void testcase4() throws IOException, AWTException, InterruptedException{
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.emaiId).sendKeys(BaseClass.readProperty("validEmail"));
		BaseClass.getWebElementByXpath(rl.checkBox).click();
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase4 - Passed ");
		}
		else {
			System.out.println("Testcase4 - Failed");
		}
	}
	@Test(priority = 2)
	public void testcase5() throws IOException, InterruptedException{
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.fullName).sendKeys(BaseClass.readProperty("validFullName"));
		BaseClass.getWebElementByXpath(rl.emaiId).sendKeys(BaseClass.readProperty("validEmail"));
		if(!BaseClass.getWebElementByXpath(rl.checkBox).isSelected()) {
			System.out.println("Testcase5 - Passed ");
		}
		else {
			System.out.println("Testcase5 - Failed");
		}
	}
	@Test(dataProvider = "testData",priority = 1)
	public void testcase6(String param1, String param2)  {
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.fullName).sendKeys(param1);
		BaseClass.getWebElementByXpath(rl.emaiId).sendKeys(param2);
		BaseClass.getWebElementByXpath(rl.checkBox).click();
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase6 - Passed ");
		}
		else {
			System.out.println("Testcase6 - Failed");
		}
	}
	@Test(dependsOnMethods = "testcase6")
	public void testcase7() throws IOException, InterruptedException {
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.fullName).sendKeys(BaseClass.readProperty("validFullName"));
		BaseClass.getWebElementByXpath(rl.emaiId).sendKeys(BaseClass.readProperty("wrongEmail"));
		BaseClass.getWebElementByXpath(rl.checkBox).click();
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase7 - Passed ");
		}
		else {
			System.out.println("Testcase7 - Failed");
		}
	}
	@Test(priority = 4)
	public void testcase8() throws IOException, InterruptedException {
		RegLocators rl = new RegLocators();
		// Perform soft assertions
		SoftAssert softAssert = new SoftAssert();

		// Soft Assert to check current and given url
		softAssert.assertTrue(driver.getCurrentUrl().contains(BaseClass.readProperty("Url")), "URL doesn't match the expected pattern");

		// Assert all soft assertions
		softAssert.assertAll();
		BaseClass.getWebElementByXpath(rl.logIn).click();
		// Explicit wait with a timeout of  seconds
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// Wait for the iframe to be available
		WebElement innerIframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("auth-login-ui")));
		driver.switchTo().frame(innerIframe);
		// Wait for the element Phone to be displayed
		WebElement elementToBeDisplayed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(rl.phoneNumber)));
		elementToBeDisplayed.sendKeys(BaseClass.readProperty("ValidUserMobile"));
		BaseClass.getWebElementByXpath(rl.sentOTP).click();
		driver.switchTo().defaultContent();
		Thread.sleep(35000);

		BaseClass.getWebElementByXpath(rl.profile).click();
		Thread.sleep(500);
		BaseClass.getWebElementByXpath(rl.logout).click();
		System.out.println("Testcase8");
	}
	@Test(priority = 3)
	public void testcase9() throws IOException, InterruptedException {
		RegLocators rl = new RegLocators();
		BaseClass.getWebElementByXpath(rl.signUp).click();
		BaseClass.getWebElementByXpath(rl.checkBox).click();
		if(!BaseClass.getWebElementByXpath(rl.createAccount).isSelected()) {
			System.out.println("Testcase9 - Passed ");
		}
		else {
			System.out.println("Testcase9 - Failed");
		}
	}
}
