package page_objects.google;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultsPage {

	protected WebDriver driver;

	@FindBy(id = "result-stats")
	private WebElement resultStats;
	@FindBy(css = "a > h3")
	private WebElement firstResult;

	public GoogleSearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void hitFirstResult() {
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(firstResult)).click();
	}

	private long extractNumberOfResults(String resultsDisplay) {
		Pattern resultsDisplayPattern = Pattern.compile("\\D*+((\\d|,|\\.|\\s)*+).*+");
		Matcher matcher = resultsDisplayPattern.matcher(resultsDisplay);
		matcher.matches();
		String stringNumberRepresentation = matcher.group(1);
		stringNumberRepresentation = stringNumberRepresentation.replaceAll("\\D", "");
		return Long.parseLong(stringNumberRepresentation);
	}

	public long getNumberOfResults() {
		String fullText = new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(resultStats)).getText();
		return extractNumberOfResults(fullText);
	}

}
