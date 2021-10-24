package page_objects.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangehrmDashboardPage extends OrangehrmUserPage {

	OrangehrmDashboardPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void waitForThisToLoad() {
		new WebDriverWait(driver, 15).until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".primary-content-container"), 12));
	}

}
