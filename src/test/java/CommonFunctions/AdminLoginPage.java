package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLoginPage 
{
	@FindBy(xpath = "//button[@id='btnreset']")
	WebElement objReset;
	@FindBy(name = "username")
	WebElement objUser;
	@FindBy(xpath  = "//input[@id='password']")
	WebElement objPass;
	@FindBy(xpath = "//button[@id='btnsubmit']")
	WebElement objLogin;
	//method for login
	public void adminLogin(String user, String pass)
	{
		objReset.click();
		objUser.sendKeys(user);
		objPass.sendKeys(pass);
		objLogin.click();
	}
	
}
