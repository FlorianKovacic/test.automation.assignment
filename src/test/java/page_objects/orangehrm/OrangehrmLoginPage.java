package page_objects.orangehrm;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrangehrmLoginPage {

	protected WebDriver driver;

	@FindBy(id = "frmLogin")
	private WebElement loginForm;

	public OrangehrmLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public OrangehrmDashboardPage logIn() {
		loginForm.submit();
		return new OrangehrmDashboardPage(driver);
	}

}
