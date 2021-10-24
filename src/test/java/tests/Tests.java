package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import page_objects.demoqa.DemoQABlankSectionPage;
import page_objects.demoqa.DemoQAHomePage;
import page_objects.demoqa.DemoQAInteractionsDroppableSectionPage;
import page_objects.demoqa.DemoQAWidgetsTooltipsSectionPage;
import page_objects.google.GoogleSearchPage;
import page_objects.google.GoogleSearchResultsPage;
import page_objects.orangehrm.OrangehrmAddCandidateForm;
import page_objects.orangehrm.OrangehrmDashboardPage;
import page_objects.orangehrm.OrangehrmLoginPage;
import page_objects.orangehrm.OrangehrmRecruitmentCandidatesPage;

public class Tests extends SeleniumTest {

	private void checkCurrentUrl(String expectedUrl) {
		assertEquals(expectedUrl, driver.getCurrentUrl());
	}

	@Test
	void testMethod1() {
		driver.get("https://www.google.com");
		GoogleSearchPage searchPage = new GoogleSearchPage(driver);
		GoogleSearchResultsPage resultsPage = searchPage.search("demoqa.com");
		resultsPage.hitFirstResult();
		checkCurrentUrl("https://demoqa.com/");

		DemoQAHomePage homePage = new DemoQAHomePage(driver);
		DemoQABlankSectionPage blankInteractionsPage = homePage.navigateToInteractions();
		DemoQAInteractionsDroppableSectionPage droppablePage = blankInteractionsPage.navigateToInteractionsDroppable();
		checkCurrentUrl("https://demoqa.com/droppable");
		droppablePage.dragAndDrop();
		System.out.println(droppablePage.getDropHereBoxText());

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File saveScreenshotHere = new File("screenshots/demoQAScreenshot.png");
		try {
			FileUtils.copyFile(screenshot, saveScreenshotHere);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Saving the screenshot failed with: " + e.getStackTrace());
		}

		DemoQAWidgetsTooltipsSectionPage tooltipsPage = droppablePage.navigateToWidgetsTooltips();
		checkCurrentUrl("https://demoqa.com/tool-tips");
		tooltipsPage.hoverAndPrintTooltip();
	}

	@Test
	void testMethod2() {
		driver.get("https://www.google.com");
		GoogleSearchPage searchPage = new GoogleSearchPage(driver);
		GoogleSearchResultsPage resultsPage = searchPage.search("cheese");
		assertEquals(777L, resultsPage.getNumberOfResults(), "There is too much cheese on the internet");
	}

	@Test
	void testMethod3() {
		driver.get("https://orangehrm-demo-7x.orangehrmlive.com/");
		OrangehrmLoginPage loginPage = new OrangehrmLoginPage(driver);
		OrangehrmDashboardPage dashboardPage = loginPage.logIn();
		dashboardPage.waitForThisToLoad();
		OrangehrmRecruitmentCandidatesPage candidatesPage = dashboardPage.navigateToRecruitmentCandidates("noncoreIframe");
		int previousNumberOfCandidates = candidatesPage.getNumberOfCandidates();
		System.out.println(previousNumberOfCandidates);
		OrangehrmAddCandidateForm addCandidateForm = candidatesPage.addCandidate();
		addCandidateForm.fillTheFormAndSubmit();
		int currentNumberOfCandidates = candidatesPage.getNumberOfCandidates();
		assertEquals(previousNumberOfCandidates + 1, currentNumberOfCandidates);
		previousNumberOfCandidates = currentNumberOfCandidates;
		candidatesPage.selectTopCandidate();
		candidatesPage.deleteSelectedCandidates();
		currentNumberOfCandidates = candidatesPage.waitUntilNumberOfCandidatesUpdates();
		assertEquals(previousNumberOfCandidates - 1, currentNumberOfCandidates);
		candidatesPage.logOut();
	}

}
