package page_objects.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQANavigationBar {

	protected WebDriver driver;

	@FindBy(css = "div.element-group:nth-child(5)")
	private WebElement interactionsSection;
	@FindBy(css = "div.element-group:nth-child(4)")
	private WebElement widgetsSection;

	DemoQANavigationBar(WebDriver driver, WebElement navigationBar) {
		this.driver = driver;
		PageFactory.initElements((field) -> new DefaultElementLocator(navigationBar, field), this);
	}

	private void navigateToSection(WebElement section) {
		WebElement menuItems = section.findElement(By.cssSelector("div.element-list"));
		if (!menuItems.getAttribute("class").contains("show")) {
			section.click();
		}
	}

	private void navigateToPage(WebElement section, int ordinal) {
		WebElement menuItem = section.findElement(By.id(String.format("item-%d", ordinal)));
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(menuItem)).click();
		} catch (ElementClickInterceptedException e) {
			//use java script to bypass overlapping
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuItem);
		}
	}

	public DemoQAInteractionsDroppableSectionPage navigateToInteractionsDroppable() {
		navigateToSection(interactionsSection);
		navigateToPage(interactionsSection, 3);
		return new DemoQAInteractionsDroppableSectionPage(driver);
	}

	public DemoQAWidgetsTooltipsSectionPage navigateToWidgetsTooltips() {
		navigateToSection(widgetsSection);
		navigateToPage(widgetsSection, 6);
		return new DemoQAWidgetsTooltipsSectionPage(driver);
	}

}
