package ru.frigesty.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.frigesty.halpers.Attach;
import java.util.Map;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void setUpBrowserConfiguration() {
        String getWdHost = format("https://user1:1234@%s/wd/hub", System.getProperty("wd", "selenoid.autotests.cloud"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        Configuration.browser = System.getProperty("browser");
        Configuration.browserVersion = System.getProperty("browserVersion");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.pageLoadStrategy = System.getProperty("loadStrategy", "eager");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://demoqa.com");
        Configuration.remote = getWdHost;
        Configuration.browserCapabilities = capabilities;
        capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                "enableVNC", true,
                "enableVideo", true
        ));

    }

    @BeforeEach
    void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.addVideo();
        if (!System.getProperty("browser").equalsIgnoreCase("firefox")) {
            Attach.pageSource();
            Attach.browserConsoleLogs();
        }
    }

    @AfterAll
    public static void tearDownWebDriver() {
        closeWebDriver();
    }
}