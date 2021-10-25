package page_objects.orangehrm;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;

public abstract class OrangehrmUserPage {

	protected WebDriver driver;

	@FindBy(id = "left-menu")
	private WebElement navigationBarElement;
	@FindBy(id = "side-menu-hamburger")
	private WebElement hamburger;

	private OrangehrmNavigationBar navigation;

	OrangehrmUserPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private OrangehrmNavigationBar getNavigation() {
		if (navigation == null) {
			navigation = new OrangehrmNavigationBar(driver, navigationBarElement);
		}
		if (!navigationBarElement.getAttribute("class").contains("menu-visible")) {
			hamburger.click();
		}
		return navigation;
	}

	private void replaceNavigationBar(String frameId) {
		WebElement iframe = driver.findElement(By.id(frameId));
		navigationBarElement = null;
		hamburger = null;
		PageFactory.initElements(field -> new DefaultElementLocator(iframe, field), this);
		navigation = null;
	}

	public OrangehrmRecruitmentCandidatesPage navigateToRecruitmentCandidates(String frameId) {
		OrangehrmRecruitmentCandidatesPage result = getNavigation().navigateToRecruitmentCandidates();
		//switch to and stay on the iframe until leaving the page (logging out)
		replaceNavigationBar(frameId);
		driver.switchTo().frame(frameId);
		return result;
	}

	public OrangehrmLoginPage logOut() {
		return getNavigation().logOut();
	}

}
