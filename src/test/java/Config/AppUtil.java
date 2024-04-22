package Config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import CommonFunctions.AdminLoginPage;
import CommonFunctions.AdminLogoutPage;

public class AppUtil 
{
	public static WebDriver driver;
	public static Properties conpro;
	@BeforeTest
	public static void setUp() throws  Throwable
	{
		conpro= new Properties();
		conpro.load(new FileInputStream("./PropertyFile/Environment.properties"));
		if(conpro.getProperty("Browser").equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(conpro.getProperty("Url"));
			//call login page class and if you want access from any page class into any classes (.initElements(driver,AdminLoginPage.class)
			
			AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
			// call login page
			login.adminLogin("admin", "master");
			
		}
		else if( conpro.getProperty("Browser").equalsIgnoreCase("firefox"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(conpro.getProperty("Url"));
			AdminLoginPage login = PageFactory.initElements(driver, AdminLoginPage.class);
			// call login page
			login.adminLogin("admin", "master");
		}
		
	}
	@AfterTest
	public static void tearDown()
	{
		AdminLogoutPage logout= PageFactory.initElements(driver, AdminLogoutPage.class);
		logout.adminLogOut();
		driver.quit();
		
	}
}
