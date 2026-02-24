package com.automation.setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

/**
 * Base test class containing common setup and teardown logic for Selenium tests
 */
public class BaseTest {
    protected WebDriver driver;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BaseTest.class.getName());

    /**
     * Setup method - runs BEFORE EACH test
     */
    @BeforeEach
    public void setUpDriver() {
        logger.info("Setting up WebDriver and test environment");
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--headless");  // Enable headless for CI
        options.addArguments("--no-sandbox");  // Required for CI environments
        options.addArguments("--disable-dev-shm-usage");  // Prevent crashes in CI

        // Initialize WebDriver
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    /**
     * Teardown method - runs AFTER EACH test
     */
    @AfterEach
    public void tearDown() {
        logger.info("Tearing down WebDriver and cleaning up");
        if (driver != null) {
            driver.quit();
        }
    }
}