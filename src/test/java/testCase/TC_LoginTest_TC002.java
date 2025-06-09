package testCase;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC_LoginTest_TC002 extends BaseClass {
	
	@Test(groups={"Sanity","Master"})
	public void verifyLogin() throws IOException
	{
		try {
		logger.info("********Starting a test case*********");
		HomePage hp=new HomePage(driver);
		hp.clickmyaccount();
		hp.clicklogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.settextemail(p.getProperty("email1"));
		lp.settextpassword(p.getProperty("password"));
		lp.clickloginbtn();
		
		MyAccountPage ap=new MyAccountPage(driver);
		boolean status=ap.isMyAccountExists();
		Assert.assertEquals(status, true);
		ap.logout();
		
		
		
		}
		catch(Exception E)
		{Assert.fail();}
		logger.info("********Finished a test case*********");
		
		
	}
	

}
