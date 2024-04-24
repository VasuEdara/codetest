package orikan;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Orikanapplication {
	
	public String baseUrl = "https://orikan-ui-automation-test.azurewebsites.net/";
	public String driverpath = "./chromedriver.exe";
	
	public WebDriver driver; 
	
	@BeforeTest(description = "Browser and Application Launch")
	public void launchApplication(String browserName) {	
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", driverpath);
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(baseUrl);
			String URL = driver.getCurrentUrl();
			System.out.println("User opened the URL:"+URL);
			String title = driver.getTitle();
			System.out.println("Page Title:"+title);
		} else if(browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", driverpath);
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(baseUrl);
			String URL = driver.getCurrentUrl();
			System.out.println("User opened the URL:"+URL);
			String title = driver.getTitle();
			System.out.println("Page Title:"+title);
		}
		else {
			System.out.println("Please enter the browser name as either chrome or ie");
		}
	}
	@Parameters({ "email","password","confirmPassword" })
	@Test(description =  "registration Info")
	public void registration(String email, String password, String confirmPassword) {
		// Email field
		driver.findElement(By.xpath("//input[@id='emailAddress']")).sendKeys(email);
		if(driver.findElement(By.xpath("(//div[@class='validation-messages']//span)[1]")).isDisplayed()) {
			String emailErrorMessage = driver.findElement(By.xpath("(//div[@class='validation-messages']//span)[1]")).getText();
			System.out.println(emailErrorMessage);
		}
		// Password field
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		if(driver.findElement(By.xpath("(//div[@class='validation-messages']//span)[2]")).isDisplayed()) {
			String passwordErrorMessage = driver.findElement(By.xpath("(//div[@class='validation-messages']//span)[2]")).getText();
			System.out.println(passwordErrorMessage);
		}
		// Confirm-Password field
		driver.findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys(confirmPassword);
		if(driver.findElement(By.xpath("(//div[@class='validation-messages']//span)[3]")).isDisplayed()) {
			String confirmPwdErrorMessage = driver.findElement(By.xpath("(//div[@class='validation-messages']//span)[3]")).getText();
			System.out.println(confirmPwdErrorMessage);
		}
		// Next button
		driver.findElement(By.xpath("//button[text()='Next']")).click();
	}
	@Parameters({"firstName","lastName","address","postCode","city","state"})
	@Test(description = "contactBasicInfo details")
	public void contactBasicInfo(String firstName, String lastName, String address, String postCode, String city, String state) {
		
		// FirstName field
		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
		if(driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='First Name is required']")).isDisplayed()) {
			String fNameErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='First Name is required']")).getText();
			System.out.println(fNameErrorMessage);
		}
		// LastName field
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
		if(driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='Last Name is required']")).isDisplayed()) {
			String lNameErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='Last Name is required']")).getText();
			System.out.println(lNameErrorMessage);
		}
		// Address Line 1 field
		driver.findElement(By.xpath("//input[@id='addressLine1']")).sendKeys(address);
		if(driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='Address Line 1 is required']")).isDisplayed()) {
			String address1ErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='Address Line 1 is required']")).getText();
			System.out.println(address1ErrorMessage);
		}
		// Post-code field
		driver.findElement(By.xpath("//input[@id='postcode']")).sendKeys(postCode);
		if(driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='Postcode is required']")).isDisplayed()) {
			String postCodeErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='Postcode is required']")).getText();
			System.out.println(postCodeErrorMessage);
		}
		// City field
		driver.findElement(By.xpath("//input[@id='city']")).sendKeys(city);
		if(driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='City is required']")).isDisplayed()) {
			String cityErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='City is required']")).getText();
			System.out.println(cityErrorMessage);
		}
		// State field
		WebElement selectState = driver.findElement(By.xpath("//select[@id='state']"));
		selectState.click();
		Select selectStateValue = new Select(selectState);
		selectStateValue.selectByVisibleText(state);
		if(driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='State is required']")).isDisplayed()) {
			String stateErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/div//span[text()='State is required']")).getText();
			System.out.println(stateErrorMessage);
		}
	}
	@Parameters({"cardHolderName","cardType","cardNumber","cvv","expiryMonth","cardExpiryYear"})
	@Test(description = "Payment details")
	public void paymentInfo(String cardHolderName, String cardType, String cardNumber, String cvv, String expiryMonth, String cardExpiryYear) {
		
		// Card Holder Name
		driver.findElement(By.xpath("//input[@id='cardHolderName']")).sendKeys(cardHolderName);
		if(driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Cardholder Name is required']")).isDisplayed()) {
			String cardHolderNameErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Cardholder Name is required']")).getText();
			System.out.println(cardHolderNameErrorMessage);
		}
		// Card Type
		if(cardType.equalsIgnoreCase("visa")) {
			driver.findElement(By.xpath("//input[@id='cardTypeVISA']")).click();
		} else if(cardType.equalsIgnoreCase("Master card")) {
			driver.findElement(By.xpath("//input[@id='cardTypeMastercard']")).click();
		}
		else if(driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Type is required']")).isDisplayed()) {
			String cardTypeErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Type is required']")).getText();
			System.out.println(cardTypeErrorMessage);
		}
		// Card Number
		driver.findElement(By.xpath("//input[@id='cardNumber']")).sendKeys(cardNumber);
		if(driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Number is required']")).isDisplayed()) {
			String cardHolderNumberErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Number is required']")).getText();
			System.out.println(cardHolderNumberErrorMessage);
		}
		// CVV
		driver.findElement(By.xpath("//input[@id='cardCVV']")).sendKeys(cvv);
		if(driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card CVV is required']")).isDisplayed()) {
			String cardCvvErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card CVV is required']")).getText();
			System.out.println(cardCvvErrorMessage);
		}
		// Card Expiry Month
		WebElement cardExpiryMonth = driver.findElement(By.xpath("//select[@id='cardExpiryMonth']"));
		Select select = new Select(cardExpiryMonth);
		select.selectByVisibleText(expiryMonth);
		if(driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Expiry Month is required']")).isDisplayed()) {
			String cardExpiryMonthErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Expiry Month is required']")).getText();
			System.out.println(cardExpiryMonthErrorMessage);
		}
		// Card Expiry Year
		driver.findElement(By.xpath("//input[@id='cardExpiryYear']")).sendKeys(cardExpiryYear);
		if(driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Expiry Year is required']")).isDisplayed()) {
			String cardExpiryYearErrorMessage = driver.findElement(By.xpath("//div[@class='form-field']/descendant::span[text()='Card Expiry Year is required']")).getText();
			System.out.println(cardExpiryYearErrorMessage);
		}
	}
	@Test(description = "Click on Terms and Conditions Check box")
	public void ClickOnTermsAndConditions() {
		if(driver.findElement(By.xpath("//input[@id='agreedToTerms']")).isEnabled()) {
			driver.findElement(By.xpath("//input[@id='agreedToTerms']")).click();
		} else {
			EventFiringWebDriver eventfiringwebdriver = new EventFiringWebDriver(driver);
			eventfiringwebdriver.executeScript("document.getElementById('termsAndConditions').scrollTop=1500");
			driver.findElement(By.xpath("//input[@id='agreedToTerms']")).click();
		}
	}
	
	@Test(description = "Verify the Completion of Registration")
	public void CompleteSection() {
		String registrationSuccessfulMessage = driver.findElement(By.xpath("//*[@class='ng-untouched ng-pristine ng-valid']")).getText();
		System.out.println(registrationSuccessfulMessage);
	}
	@Test(description = "clickOnSubmitButton")
	public void clickOnSubmitButton() {
		driver.findElement(By.xpath("//button[text()='Submit']")).click();
		System.out.println("Clicked on Submit button");
		if(driver.findElement(By.xpath("//span[text()='You must agree to these terms and conditions before submitting']")).isDisplayed()) {
			String termsAndConditionsErrorMessage = driver.findElement(By.xpath("//span[text()='You must agree to these terms and conditions before submitting']")).getText();
			System.out.println(termsAndConditionsErrorMessage);
		}
	}
	@BeforeMethod(description = "clickOnBackButton")
	public void clickOnBackButton() {
		if(driver.findElement(By.xpath("//button[text()='Back']")).isDisplayed()) {
			driver.findElement(By.xpath("//button[text()='Back']")).click();
			System.out.println("Clicked on Back button");
		}
	}
	@AfterMethod(description = "clickOnNextButton")
	public void clickOnNextButton() {
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		System.out.println("Clicked on Next button");
	}
	@AfterTest(description = "close browser")
	public void browserClose() {
		driver.quit();
	}
}
