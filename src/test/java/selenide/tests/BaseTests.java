package selenide.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import selenide.helpers.CustomAllureSelenide;

import static selenide.helpers.Properties.browserConfigurationProperties;
import static selenide.helpers.Properties.timeProperties;

public class BaseTests {
    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide",
                new CustomAllureSelenide().screenshots(true).savePageSource(true));
    }

    @BeforeEach
    public void options() {
        Configuration.timeout = timeProperties.getSmallTimeout();

        Configuration.browserPosition = browserConfigurationProperties.browserPosition();
        // Configuration.browserSize = browserConfigurationProperties.browserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY,
                new ChromeOptions()
                        .addArguments("--start-maximized")
                        .addArguments("--disable-extensions"));
        Configuration.browserCapabilities = capabilities;
    }
}
