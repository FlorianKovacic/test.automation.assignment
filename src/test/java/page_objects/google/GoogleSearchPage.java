package page_objects.google;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchPage {

	protected WebDriver driver;

	@FindBy(name = "q")
	private WebElement searchInputField;

	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public GoogleSearchResultsPage search(String query) {
		searchInputField.clear();
		searchInputField.sendKeys(query + Keys.ENTER);
		return new GoogleSearchResultsPage(driver);
	}

}
