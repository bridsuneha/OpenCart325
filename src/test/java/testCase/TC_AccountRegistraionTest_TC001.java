package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationpPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_AccountRegistraionTest_TC001 extends BaseClass {
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		logger.info("Test case TC_AccountRegistraionTest_TC001 starting....");
		try {
		HomePage hp=new HomePage(driver);
		logger.info("MyAccount is clicked.....");
		hp.clickmyaccount();
		hp.clickregister();
		logger.info("Register link clicked......");
		AccountRegistrationpPage ar=new AccountRegistrationpPage(driver);
		logger.info("Customers details provided........");
		ar.setfname(randomString());
		ar.setlname(randomString());
		ar.setmail(randomString()+"@gmail.com");
		ar.setTelephone(randomNumber());
		ar.setpassword("Hridit19046@");
		ar.confpass("Hridit19046@");
		ar.setpolbutn();
		ar.clickconbutton();
		logger.info("confirm button clicked");
		
		String mes=ar.getconfmsg();
		if(mes.equals("Your Account Has Been Created!") )
		{
			
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.error("Test Failed");
			logger.debug("Debug logs...");
			Assert.assertTrue(false);
		}

		}
		catch(Exception e)
		{
			
			Assert.fail();
		}
		
		logger.info("Test case TC_AccountRegistraionTest_TC001 completed");
}
	
	
	
}
