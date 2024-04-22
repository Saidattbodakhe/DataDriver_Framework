package driverFactory;

import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.AddCustomerPage;
import Config.AppUtil;
import utilities.ExcelFileUtil;

public class AppTest extends AppUtil
{
	String inputpath="./FileInput/CustomerData.xlsx";
	String outpurpath="./FileOutput/DataDriverResults.xlsx";
	String TestData = "AddCustomer";
	ExtentReports report;
	ExtentTest logger;
	@Test
	public void startTest() throws Throwable
	{
		report = new ExtentReports("./target/Reports/Customer.html");
		//call add customer page class
		AddCustomerPage customer = PageFactory.initElements(driver, AddCustomerPage.class);
		ExcelFileUtil xl=new ExcelFileUtil(inputpath);
		int rc=xl.rowCount(TestData);
		Reporter.log("no of rows are : "+rc, true);
		for(int i=1; i<=rc; i++)
		{
			logger = report.startTest("Add Customer");
			String customerName=xl.getCellData(TestData, i, 0);
			String address =xl.getCellData(TestData, i, 1);
			String city= xl.getCellData(TestData, i, 2);
			String country = xl.getCellData(TestData, i, 3);
			String contactPerson=xl.getCellData(TestData, i, 4);
			String phoneNumbe= xl.getCellData(TestData, i, 5);
			String email =xl.getCellData(TestData, i, 6);
			String mobileNum=xl.getCellData(TestData, i, 7);
			String notes =xl.getCellData(TestData, i, 8);
			logger.log(LogStatus.INFO, customerName+ "  "+ address+"  "+city+"  "+country+"   "+
			contactPerson+ "  "+phoneNumbe+"  "+email+ "  "+mobileNum+"  "+notes);
			boolean res= customer.addCustomer(customerName, address, city, country, contactPerson,phoneNumbe, email, mobileNum, notes);
			if(res)
			{
				//if ress true write as pass in status cell sheet
				xl.setCellData(TestData, i, 9, "pass", outpurpath );
				logger.log(LogStatus.PASS, "Add Customer is success");
			}
			else
			{
				//if ress true write as Fail in status cell sheet
				xl.setCellData(TestData, i, 9, "Fail", outpurpath );
				logger.log(LogStatus.FAIL, "Add Customer is success");
			}
			report.endTest(logger);
			report.flush();
		}
	}
}
