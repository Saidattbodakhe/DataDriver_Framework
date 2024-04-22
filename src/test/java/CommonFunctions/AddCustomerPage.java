package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v119.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Report;

import io.reactivex.rxjava3.functions.Action;

public class AddCustomerPage 
{
	WebDriver driver;
	//constructor for invlking webdriver methods
	public AddCustomerPage(WebDriver driver)
	{
		this.driver=driver;
	}
	//define repository for add customer
	@FindBy(xpath = "(//a[contains(.,'Customers')])[2]")
	WebElement objclickCustomerLink;
	@FindBy(xpath = "(//span[@data-caption='Add'])[1]")
	WebElement objClickAddIcon;
	@FindBy(name = "x_Customer_Number")
	WebElement objCustomerNumber;
	@FindBy(id = "x_Customer_Name")
	WebElement objCustemerName;
	@FindBy(xpath = "//textarea[@id='x_Address']")
	WebElement objAddress;
	@FindBy(name = "x_City")
	WebElement objCity;
	@FindBy(id = "x_Country")
	WebElement objCountry;
	@FindBy(xpath = "//input[@id='x_Contact_Person']")
	WebElement objContactPerson;
	@FindBy(name= "x_Phone_Number")
	WebElement objPhoneNumber;
	@FindBy(name = "x__Email")
	WebElement objEmail;
	@FindBy (xpath = "//input[@id='x_Mobile_Number']")
	WebElement objMobileNum;
	@FindBy(id="x_Notes")
	WebElement objNote;
	@FindBy(id = "btnAction")
	WebElement objAddButton;
	@FindBy (xpath = "//button[normalize-space()='OK!']")
	WebElement objClickConfiOk;
	@FindBy (xpath = "(//button[starts-with(text(),'OK')])[6]")
	WebElement objClickAlerOk;
	@FindBy(xpath = "btnCancel")
	WebElement objCancelButton;
	@FindBy(xpath = "//span[@data-caption='Search']")
	WebElement objSearchPanel;
	@FindBy(xpath = "//input[@id='psearch']")
	WebElement objSearchTextbox;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objSearchbutton;
	@FindBy(xpath = "//table[@class='table ewTable']/tbody/tr[1]/td[5]/div/span/span")
	WebElement customerTable;
	
	//method for add Customer/ enter custemer data
	public boolean addCustomer(String cname, String Address, String city, String country, String coPerson, String pnumber,
			String Email,String Mnumber, String Notes) throws Throwable 

	
	{
		Actions ac=new Actions(driver);
		ac.moveToElement(this.objclickCustomerLink).click().perform();
		ac.moveToElement(this.objClickAddIcon).click().perform();
		//capture custNumber
		String Exp_Date=this.objCustomerNumber.getAttribute("value");
		this.objCustemerName.sendKeys(cname);
		this.objAddress.sendKeys(Address);
		this.objCity.sendKeys(city);
		this.objCountry.sendKeys(country);
		this.objContactPerson.sendKeys(coPerson);
		this.objPhoneNumber.sendKeys(pnumber);
		this.objEmail.sendKeys(Email);
		this.objMobileNum.sendKeys(Mnumber);
		this.objNote.sendKeys(Notes);
//		this.objAddButton.sendKeys(key);
		ac.moveToElement(this.objAddButton).click().perform();
		this.objClickConfiOk.click();
		Thread.sleep(3000);
		this.objClickAlerOk.click();
		Thread.sleep(3000);
		
		if(!this.objSearchTextbox.isDisplayed())
			this.objSearchPanel.click();
		this.objSearchTextbox.clear();
		this.objSearchTextbox.sendKeys(Exp_Date);
		this.objSearchbutton.click();
		String Act_Data=this.customerTable.getText();
		if(Act_Data.equals(Exp_Date))
		{
			Reporter.log(Act_Data+ "     "+Exp_Date+"    "+"customer number matching", true);
			return true;
		}
		else
		{
			Reporter.log(Act_Data+"    "+Exp_Date+"    "+"customer number is not matching", true);
		    return false;
		}
	}
}
