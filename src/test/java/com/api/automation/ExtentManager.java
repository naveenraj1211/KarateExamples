package com.api.automation;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;


public class ExtentManager {

	private static ExtentReports extent;

	public static ExtentReports getInstance() {
		return extent;
	}

	public static void createInstance(String fileName) {
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);

		htmlReporter.config().setDocumentTitle("LetTest&Automate");
		htmlReporter.config().setTheme(Theme.STANDARD);
		htmlReporter.config().setEncoding("UTF-8");
		htmlReporter.config().setProtocol(Protocol.HTTPS);
		htmlReporter.config().setReportName(System.getProperty("user.name"));
		htmlReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss a");
		htmlReporter.viewConfigurer().viewOrder().as(new ViewName[] { ViewName.DASHBOARD, ViewName.TEST,
				// ViewName.TAG,
				ViewName.AUTHOR, ViewName.DEVICE, ViewName.EXCEPTION, ViewName.LOG }).apply();

		extent = new ExtentReports();
		extent.setSystemInfo("Created By", "LetTest&Automate");
		extent.setSystemInfo("AutmationFramework", "API");
		extent.setSystemInfo("Release", "1.1.0");
		extent.setSystemInfo("Project", "Test Karate Project");
		extent.attachReporter(htmlReporter);
	}

	public static void createReport() {

		System.out.println("Extent report was called to initialize");
		
		 if(ExtentManager.getInstance() == null)
		 {
	            Date date = new Date();
	            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy-HH-mm-ss");
	            String formattedDate = dateFormat.format(date);
	            ExtentManager.createInstance("results/" + "Extent_Report_" + formattedDate + ".html");
	      }
	}
}