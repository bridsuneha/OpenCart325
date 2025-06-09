package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(id="input-email")WebElement textemail;
	@FindBy(id="input-password") WebElement textpassword;
	@FindBy(xpath="//input[@value='Login']")WebElement btnlogin;
	
	public void settextemail(String email) 
	{textemail.sendKeys(email);}
	public void settextpassword(String pass) 
	{textpassword.sendKeys(pass);}
	public void clickloginbtn() 
	{btnlogin.click();}
	
	

}
