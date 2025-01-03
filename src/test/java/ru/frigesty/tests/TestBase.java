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
import static com.codeborne.selenide.Selenide.open;
import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void setUpBrowserConfiguration() {
        String wdHost = System.getProperty("wd", "selenoid.autotests.cloud");
        String getWdHost = format("https://user1:1234@%s/wd/hub", wdHost);
        String[] browser = System.getProperty("browser").split(":");

        DesiredCapabilities capabilities = new DesiredCapabilities();
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
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }

    @AfterAll
    public static void tearDownWebDriver() {
        closeWebDriver();
    }
}