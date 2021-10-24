package page_objects.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class DemoQASectionPage {

	protected WebDriver driver;

	@FindBy(css = ".left-pannel")
	private WebElement navigationBarElement;

	private DemoQANavigationBar navigation;

	DemoQASectionPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private DemoQANavigationBar getNavigation() {
		if (navigation == null) {
			navigation = new DemoQANavigationBar(driver, navigationBarElement);
		}
		return navigation;
	}

	public DemoQAInteractionsDroppableSectionPage navigateToInteractionsDroppable() {
		return getNavigation().navigateToInteractionsDroppable();
	}

	public DemoQAWidgetsTooltipsSectionPage navigateToWidgetsTooltips() {
		return getNavigation().navigateToWidgetsTooltips();
	}

}
