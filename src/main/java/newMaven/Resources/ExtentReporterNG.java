package newMaven.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

	public static ExtentReports getReportsObjects()
	{
		String dirPath=System.getProperty("user.dir")+"//reports//index.html";
		ExtentSparkReporter sparkReport=new ExtentSparkReporter(dirPath);
		sparkReport.config().setReportName("Automation results");
		sparkReport.config().setDocumentTitle("Automation Project");
		
		ExtentReports extentReport= new ExtentReports();
		extentReport.attachReporter(sparkReport);
		extentReport.setSystemInfo("Tester", "Kuldeep Singh");
		return extentReport;
		//extentReport.createTest(dirPath);
	}
	

}
