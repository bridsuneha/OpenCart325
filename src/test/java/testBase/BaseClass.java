package testBase;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
	//loading properties files
	FileReader file=new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.property");
	p=new Properties();
	p.load(file);
	logger= LogManager.getLogger(this.getClass());
	if(p.getProperty("execution_env1").equalsIgnoreCase("remote"))
	{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{
			capabilities.setPlatform(Platform.WIN11);
		}
		else if (os.equalsIgnoreCase("Linux"))
		{
			capabilities.setPlatform(Platform.LINUX);
		}
		else
		{
			System.out.println("No matching os");
			return;
		}
		
		//browser
		switch(br.toLowerCase())
		{
		case "chrome": capabilities.setBrowserName("chrome"); break;
		case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
		case "firefox":capabilities.setBrowserName("firefox");break;
		default: System.out.println("No matching browser"); return;
		}
		
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
	}
	
			
	if(p.getProperty("execution_env1").equalsIgnoreCase("local"))
	{

		switch(br.toLowerCase())
		{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name.."); return;
		}
	}
	
	//driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(p.getProperty("appURL1"));
		driver.manage().window().maximize();
			
	}

	@AfterClass(groups= {"Regression","Sanity","Master"})
	public void teardown()
	
	{
		driver.quit();
	}
	public String randomString()
	{
		String generatedString= RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumber()
	{
		String generatedNumber= RandomStringUtils.randomNumeric(5);
		return generatedNumber;
	}
	public String randomAlphanumeric()
	{
		String generatedalphanumeric= RandomStringUtils.randomAlphanumeric(10);
		return generatedalphanumeric;
	}
	
	public String capturescreenshot(String tname)
	{
		TakesScreenshot tk=(TakesScreenshot)driver;
		File sourceFile=tk.getScreenshotAs(OutputType.FILE);
		SimpleDateFormat dte=new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date dt=new Date();
		String currentdate=dte.format(dt);
		String targetfilepath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+currentdate+".png";
		File targetfile=new File(targetfilepath);
		sourceFile.renameTo(targetfile);
		return targetfilepath;
	}
	

}
