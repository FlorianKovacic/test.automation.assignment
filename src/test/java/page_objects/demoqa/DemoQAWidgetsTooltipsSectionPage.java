package page_objects.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoQAWidgetsTooltipsSectionPage extends DemoQASectionPage {

	@FindBy(id = "toolTipButton")
	private WebElement hoverMeButton;
	@FindBy(id = "buttonToolTip")
	private WebElement buttonTooltip;

	DemoQAWidgetsTooltipsSectionPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void hoverAndPrintTooltip() {
		Actions actionProvider = new Actions(driver);
		actionProvider.moveToElement(hoverMeButton).build().perform();
		String tooltipText = new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(buttonTooltip)).getText();
		System.out.println(tooltipText);
	}

}
