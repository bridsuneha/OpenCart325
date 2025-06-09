package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	
	 public ExtentSparkReporter sparkReporter;
	 public ExtentReports extent;
	 public ExtentTest test;
	 String repname;
	 
	 public void onStart(ITestContext context)
	 {
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");//timestamp format
		Date dt=new Date();//current date
		String currentdatetimestamp=df.format(dt);//formating date into required timestamp
		*/
		 String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repname="Test-Report-" + timeStamp + ".html";//name of the report
	
		
		
		
		sparkReporter=new ExtentSparkReporter(".\\reports\\"+repname);
		sparkReporter.config().setDocumentTitle("opencart Automation Report");
		sparkReporter.config().setReportName("opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "OpenCart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environemnt", "QA");
		
		String os=context.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser=context.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser",browser );
		
		List<String> includedGroups=context.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{extent.setSystemInfo("Groups", includedGroups.toString());}
		
	 }
	 public void onTestSuccess(ITestResult result) 
	 {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.PASS, result.getTestClass().getName()+"successfuly passed");
	 }
	 
	 public void onTestFailure(ITestResult result)
	 {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL,result.getTestClass().getName()+"is failed");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 try
		 {BaseClass bc=new BaseClass();
		String imgpath= bc.capturescreenshot(result.getName());
		test.addScreenCaptureFromPath(imgpath);}
		 catch(Exception e)
		 {e.printStackTrace();}
	 }
	 
	 public void onTestSkipped(ITestResult result)
	 {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, result.getName()+"got skipped");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 
		 
		 
	 }
	 
	 
	 public void onFinish(ITestContext context)
	 {
		 extent.flush();
		 
		 
		 String pathofextentreport=System.getProperty("user.dir")+"\\reports\\"+repname;
				// System.getProperty("user.dir")+"/reports/"+repname;
		 File extentReport=new File(pathofextentreport);
		try { 
		 Desktop.getDesktop().browse(extentReport.toURI());
		 }
		catch(IOException e)
		{
			e.printStackTrace();
		}
		 
	 }
	 

}
