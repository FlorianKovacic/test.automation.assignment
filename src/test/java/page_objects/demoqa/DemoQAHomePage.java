package page_objects.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQAHomePage {

	protected WebDriver driver;

	@FindBy(css = "div.card:nth-child(5)")
	private WebElement interactionsSection;

	public DemoQAHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public DemoQABlankSectionPage navigateToInteractions() {
		interactionsSection.click();
		return new DemoQABlankSectionPage(driver);
	}

}
