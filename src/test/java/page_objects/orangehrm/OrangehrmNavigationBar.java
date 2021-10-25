package page_objects.orangehrm;

import java.util.function.UnaryOperator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrangehrmNavigationBar {

	protected WebDriver driver;

	@FindBy(id = "menu_recruitment_viewRecruitmentModule")
	private WebElement recruitmentSection;
	@FindBy(id = "menu-profile")
	private WebElement profileMenu;

	OrangehrmNavigationBar(WebDriver driver, WebElement navigationBar) {
		this.driver = driver;
		PageFactory.initElements(field -> new DefaultElementLocator(navigationBar, field), this);
	}

	/**
	 * Serves as a generalization of navigating to a menu on the side bar.
	 * 
	 * @param section
	 *            The menu that is reached.
	 * @param displayerLocator
	 *            The element that opens the menu.
	 * @param displayLocator
	 *            The element that indicates whether the menu is currently active.
	 */
	private void navigateToSection(WebElement section, UnaryOperator<WebElement> displayerLocator, UnaryOperator<WebElement> displayLocator) {
		if (!displayLocator.apply(section).getAttribute("class").contains("active")) {
			displayerLocator.apply(section).click();
		}
	}

	/**
	 * Serves as a generalization of clicking on a menu entry.
	 * 
	 * @param section
	 *            The menu which is the context of this call.
	 * @param id
	 *            Id based locator of the entry.
	 */
	private void navigateToPage(WebElement section, String id) {
		WebElement menuItem = section.findElement(By.id(id));
		new WebDriverWait(driver, 2).until(ExpectedConditions.elementToBeClickable(menuItem)).click();
	}

	public OrangehrmRecruitmentCandidatesPage navigateToRecruitmentCandidates() {
		navigateToSection(recruitmentSection, section -> section, section -> section);
		navigateToPage(recruitmentSection, "menu_recruitment_viewCandidates");
		return new OrangehrmRecruitmentCandidatesPage(driver);
	}

	public OrangehrmLoginPage logOut() {
		navigateToSection(profileMenu, section -> section.findElement(By.id("user-dropdown")), section -> section.findElement(By.id("user_menu")));
		navigateToPage(profileMenu, "logoutLink");
		return new OrangehrmLoginPage(driver);
	}

}
