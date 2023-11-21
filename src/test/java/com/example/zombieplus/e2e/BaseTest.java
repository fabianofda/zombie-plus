package com.example.zombieplus.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public class BaseTest {
    protected ChromeDriver driver;
    @BeforeEach
    public void setUp() {
        setupWebDriver();
        configureBrowser();
    }
    private void setupWebDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    private void configureBrowser() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
