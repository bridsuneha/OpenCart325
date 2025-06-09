package testCase;


import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Data is valid -login success-test pass-logout
 Data is valid-login failed-test fail
 
 data is invalid-login success-test fail
 data is invalid-login failed-test pass
 
 */



public class TC003_LoginDDT extends BaseClass
{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="datadriven")
	public void Verify_Login (String email,String pas,String ExpRes)
	{
		//logger.info("********Starting a test case*********");
		
	
		HomePage hp=new HomePage(driver);
		hp.clickmyaccount();
		hp.clicklogin();
	
	
		LoginPage lp=new LoginPage(driver);
		lp.settextemail(email);
		lp.settextpassword(pas);
		lp.clickloginbtn();
		
		MyAccountPage ap=new MyAccountPage(driver);
		boolean targetPage=ap.isMyAccountExists();
		
	if(ExpRes.equalsIgnoreCase("Valid"))
	{
		if(targetPage==true)
		{
			ap.logout();
			Assert.assertTrue(true);
			
		}
		else
		{
			Assert.assertTrue(false);
		}
	}
	if(ExpRes.equalsIgnoreCase("Invalid"))
	{
		if(targetPage==true)
		{
			ap.logout();
			Assert.assertTrue(false);			
		}
		else
		{
			Assert.assertTrue(true);
		}

	
	
}}
	

	}
	

