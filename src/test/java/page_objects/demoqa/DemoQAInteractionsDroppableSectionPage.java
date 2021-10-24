package page_objects.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DemoQAInteractionsDroppableSectionPage extends DemoQASectionPage {

	@FindBy(id = "draggable")
	private WebElement draggable;
	@FindBy(id = "droppable")
	private WebElement droppable;

	DemoQAInteractionsDroppableSectionPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public void dragAndDrop() {
		Actions actionProvider = new Actions(driver);
		actionProvider.dragAndDrop(draggable, droppable).build().perform();
	}

	public String getDropHereBoxText() {
		return droppable.findElement(By.tagName("p")).getText();
	}

}
