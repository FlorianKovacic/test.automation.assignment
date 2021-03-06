package page_objects.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangehrmRecruitmentCandidatesPage extends OrangehrmUserPage {

	@FindBy(id = "noncoreIframe")
	private WebElement iframe;
	@FindBy(id = "fromToOf")
	private WebElement candidatesCounter;
	@FindBy(id = "addItemBtn")
	private WebElement addCandidateButton;

	@FindBy(css = "table.pagedata > tbody > tr:nth-child(1) > td:nth-child(1)")
	private WebElement topCandidateCheckbox;
	@FindBy(id = "ohrmList_Menu")
	private WebElement threeDots;
	@FindBy(id = "deleteItemBtn")
	private WebElement deleteCandidateButton;
	@FindBy(id = "candidate-delete-button")
	private WebElement confirmDeletionButton;

	OrangehrmRecruitmentCandidatesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private String getNumberOfCandidatesString() {
		String fullText = new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(candidatesCounter)).getText();
		return fullText;
	}

	public int getNumberOfCandidates() {
		String fullText = getNumberOfCandidatesString();
		return Integer.parseInt(fullText.substring(fullText.indexOf("of") + 3));
	}

	//Inconvenient wait for the value to update
	public int waitUntilNumberOfCandidatesUpdates() {
		String outdatedValue = getNumberOfCandidatesString();
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(candidatesCounter, outdatedValue)));
		} catch (TimeoutException e) {
			;
		}
		int updatedValue = getNumberOfCandidates();
		return updatedValue;
	}

	public OrangehrmAddCandidateForm addCandidate() {
		addCandidateButton.click();
		return new OrangehrmAddCandidateForm(driver, driver.findElement(By.id("modalAddCandidate")));
	}

	public void selectTopCandidate() {
		topCandidateCheckbox.click();
	}

	public void deleteSelectedCandidates() {
		threeDots.click();
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(deleteCandidateButton)).click();
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(confirmDeletionButton)).click();
	}

}
