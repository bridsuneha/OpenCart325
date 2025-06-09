package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationpPage extends BasePage{
	
	public AccountRegistrationpPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy (id="input-firstname")
	WebElement firstName;
	@FindBy (id="input-lastname")
	WebElement lastName;
	@FindBy (id="input-email")
	WebElement email;
	@FindBy(id="input-telephone")
	WebElement telephone;
	@FindBy (id="input-password")
	WebElement pass;
	@FindBy(id="input-confirm")
	WebElement Conf_pass;
	@FindBy (name="agree")
	WebElement polbutton;
	@FindBy (xpath="//input[@value='Continue']")
	WebElement conbutton;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement message;
	
	public void setfname(String fname)
	{
		firstName.sendKeys(fname);
	}
	public void setlname(String lname)
	{
		lastName.sendKeys(lname);
	}
	public void setmail(String mail)
	{
		email.sendKeys(mail);
	}
	public void setTelephone(String tphone)
	{
		telephone.sendKeys(tphone);
	}
	public void setpassword(String pwd)
	{
		pass.sendKeys(pwd);
	}
	public void confpass(String pwd)
	{
		Conf_pass.sendKeys(pwd);
	}
	public void setpolbutn()
	{
		polbutton.click();
	}
	public void clickconbutton()
	{
		conbutton.click();
	}
	public String getconfmsg()
	{
		return message.getText();
	}
	
	
	
	
	
	
	

}
