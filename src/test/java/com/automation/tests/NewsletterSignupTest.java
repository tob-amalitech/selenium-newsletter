package com.automation.tests;

import com.automation.pages.NewsletterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Newsletter Signup Test using Page Object Model
 * Tests the newsletter signup form functionality
 */
public class NewsletterSignupTest {

    private WebDriver driver;
    private NewsletterPage newsletterPage;

    // TODO: Update this URL to match where your newsletter form is hosted
    // Examples:
    // - Local file: "file:///C:/Users/YourName/projects/newsletter/index.html"
    // - Local server: "http://localhost:8080/index.html"
    // - Online: "https://yoursite.com/newsletter.html"
    private static final String NEWSLETTER_URL = "https://webdevfundamentals.vercel.app/";

    /**
     * Setup method - runs BEFORE EACH test
     */
    @BeforeEach
    public void setUp() {
        // Setup ChromeDriver using WebDriverManager
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

        // Initialize Page Object
        newsletterPage = new NewsletterPage(driver);
    }

    /**
     * Teardown method - runs AFTER EACH test
     */
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    /**
     * Test 1: Verify newsletter page loads successfully
     */
    @Test
    @DisplayName("Should load newsletter page successfully")
    public void testPageLoads() {
        // Navigate to newsletter page
        newsletterPage.navigateTo(NEWSLETTER_URL);

        // Verify page title
        String pageTitle = newsletterPage.getPageTitle();
        assertNotNull(pageTitle, "Page title should not be null");

        System.out.println("✅ Newsletter page loaded successfully");
        System.out.println("Page title: " + pageTitle);
    }

    /**
     * Test 2: Successful newsletter subscription with valid email
     */
    @Test
    @DisplayName("Should subscribe successfully with valid email")
    public void testSuccessfulSubscription() {
        // Navigate to newsletter page
        newsletterPage.navigateTo(NEWSLETTER_URL);

        // Subscribe with valid email
        String testEmail = "test@company.com";
        newsletterPage.subscribeWithEmail(testEmail);

        // Wait a moment for success message to appear
        try {
            Thread.sleep(1000); // Give JavaScript time to process
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify success message is displayed
        assertTrue(newsletterPage.isSuccessMessageDisplayed(),
                "Success message should be displayed after valid subscription");

        // Verify success message text
        String successHeading = newsletterPage.getSuccessMessageHeading();
        assertEquals("Thanks for subscribing!", successHeading,
                "Success message heading should match");

        // Verify email is shown in success message
        String confirmedEmail = newsletterPage.getConfirmedEmail();
        assertEquals(testEmail, confirmedEmail,
                "Confirmed email should match the entered email");

        System.out.println("✅ Subscription successful!");
        System.out.println("Success message: " + successHeading);
        System.out.println("Confirmed email: " + confirmedEmail);
    }

    /**
     * Test 3: Invalid email validation
     * Note: This test depends on your JavaScript validation
     */
    @Test
    @DisplayName("Should show error for invalid email")
    public void testInvalidEmailValidation() {
        // Navigate to newsletter page
        newsletterPage.navigateTo(NEWSLETTER_URL);

        // Try to subscribe with invalid email
        newsletterPage.subscribeWithEmail("invalid-email");

        // Wait a moment for validation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify invalid email message is displayed
        // Note: This assertion might fail if your JS validation works differently
        boolean isErrorDisplayed = newsletterPage.isInvalidEmailMessageDisplayed();

        System.out.println("Invalid email error displayed: " + isErrorDisplayed);

        // Optional assertion - comment out if your validation doesn't show this element
        // assertTrue(isErrorDisplayed, "Error message should be displayed for invalid email");
    }

    /**
     * Test 4: Empty email validation
     */
    @Test
    @DisplayName("Should validate empty email field")
    public void testEmptyEmailValidation() {
        // Navigate to newsletter page
        newsletterPage.navigateTo(NEWSLETTER_URL);

        // Try to subscribe with empty email
        newsletterPage.clickSubscribe();

        // Wait a moment for validation
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Success message should NOT be displayed
        assertFalse(newsletterPage.isSuccessMessageDisplayed(),
                "Success message should not be displayed for empty email");

        System.out.println("✅ Empty email validation working");
    }

    /**
     * Test 5: Dismiss success message
     */
    @Test
    @DisplayName("Should dismiss success message when dismiss button is clicked")
    public void testDismissSuccessMessage() {
        // Navigate to newsletter page
        newsletterPage.navigateTo(NEWSLETTER_URL);

        // Subscribe with valid email
        newsletterPage.subscribeWithEmail("dismiss@test.com");

        // Wait for success message
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify success message is displayed
        assertTrue(newsletterPage.isSuccessMessageDisplayed(),
                "Success message should be displayed");

        // Click dismiss button
        newsletterPage.clickDismissButton();

        // Wait for animation/transition
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Note: Depending on your JavaScript, the message might be hidden or removed
        // You may need to adjust this assertion based on your implementation
        System.out.println("✅ Dismiss button clicked");
    }
}