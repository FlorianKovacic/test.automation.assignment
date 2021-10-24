package page_objects.orangehrm;

import java.util.Calendar;
import java.util.Locale;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangehrmAddCandidateForm {

	protected WebDriver driver;

	//private OrangehrmRecruitmentCandidatesPage parentPage;

	@FindBy(id = "addCandidate_resume")
	private WebElement addResumeButton;
	@FindBy(id = "addCandidate_firstName")
	private WebElement firstNameField;
	@FindBy(id = "addCandidate_lastName")
	private WebElement lastNameField;
	@FindBy(id = "addCandidate_email")
	private WebElement emailField;
	@FindBy(id = "textarea_addCandidate_vacancy")
	private WebElement vacancySelect;
	@FindBy(css = "div#textarea_addCandidate_vacancy + ul > div > li:nth-child(10)")
	private WebElement ourVacancyChoice;
	@FindBy(id = "saveCandidateButton")
	private WebElement saveCandidateButton;

	OrangehrmAddCandidateForm(WebDriver driver, /* OrangehrmRecruitmentCandidatesPage parentPage,*/ WebElement formElement) {
		this.driver = driver;
		//this.parentPage = parentPage;
		PageFactory.initElements(field -> new DefaultElementLocator(formElement, field), this);
	}

	public void fillTheFormAndSubmit() {
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.btn")));
		addResumeButton.sendKeys(System.getProperty("user.dir") + "/src/test/resources/myWonderfulResume.txt");
		String firstName = "QA Automation - ";
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(firstNameField)).sendKeys(firstName);
		String lastName = Calendar.getInstance(Locale.US).getTime().toString();
		lastNameField.sendKeys(lastName);
		emailField.sendKeys("e.mail@gmail.com");
		vacancySelect.click();
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(ourVacancyChoice)).click();
		saveCandidateButton.click();
		//parentPage.switchBackFromIframe();
	}

}
