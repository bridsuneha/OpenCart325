package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {
	

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkmyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkregister;
	@FindBy(linkText="Login")
	WebElement lnklogin;
	
	public void clickmyaccount()
	{
		lnkmyaccount.click();
	}
	public void clickregister()
	{
		lnkregister.click();
	}
	public void clicklogin() {
		lnklogin.click();
		
	}
	

}
