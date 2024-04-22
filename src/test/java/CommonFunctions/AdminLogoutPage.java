package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogoutPage 
{
	@FindBy(xpath = "(//a[starts-with(text(),' Logout')])[2]")
	WebElement objLogout;

	//method for logout
	public void adminLogOut()
	{
		objLogout.click();
	}
}
