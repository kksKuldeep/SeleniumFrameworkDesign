package newmaven.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import newMaven.Resources.ExtentReporterNG;

public class ListenersClass extends BaseClass implements ITestListener {
	ExtentTest test;
	ExtentReports extentReport = ExtentReporterNG.getReportsObjects();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal<ExtentTest>(); //thread in Safe mode

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extentReport.createTest(result.getMethod().getMethodName());
		extentTest.set(test); //assigns unique thread id for each object test(for failed Tests)->test

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.PASS, "Test passed successfully");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//test.log(Status.FAIL, "Test Failed");
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		try {
			driver=(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// Take screen and Attached to reports
		String srcFilePath = null;
		try {
			srcFilePath = getScreenshots(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(srcFilePath, result.getMethod().getMethodName());
		

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		extentTest.get().log(Status.SKIP, "Test Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentReport.flush();
	}

}
