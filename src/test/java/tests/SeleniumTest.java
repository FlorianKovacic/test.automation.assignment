package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public abstract class SeleniumTest {

	protected WebDriver driver;

	private void createNewFirefoxDriverInstance() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("intl.accept_languages", "en-us");
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);
		driver = new FirefoxDriver(options);
	}

	private void createNewChromeDriverInstance() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("lang=en-us");
		driver = new ChromeDriver(options);
	}

	@BeforeEach
	void createNewWebDriverInstance() {
		//createNewFirefoxDriverInstance();
		createNewChromeDriverInstance();
	}

	@AfterEach
	void closeWebDriver() {
		driver.quit();
	}

}
