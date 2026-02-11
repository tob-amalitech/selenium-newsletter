package com.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page Object for Newsletter Signup Form
 * Contains all elements and actions for the newsletter page
 */
public class NewsletterPage extends BasePage {

    // Page Elements using @FindBy annotations (Page Factory pattern)

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "submit-btn")
    private WebElement submitButton;

    @FindBy(id = "invalid-email")
    private WebElement invalidEmailMessage;

    @FindBy(id = "confirmed-message")
    private WebElement confirmedMessageContainer;

    @FindBy(id = "user-email")
    private WebElement userEmailDisplay;

    @FindBy(id = "dismiss-message")
    private WebElement dismissButton;

    @FindBy(css = ".confirmed-message h2")
    private WebElement successMessageHeading;

    /**
     * Constructor
     * @param driver WebDriver instance
     */
    public NewsletterPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Navigate to the newsletter page
     * @param url URL of the newsletter form
     */
    public void navigateTo(String url) {
        driver.get(url);
    }

    /**
     * Enter email address in the input field
     * @param email Email address to enter
     */
    public void enterEmail(String email) {
        enterText(emailInput, email);
    }

    /**
     * Click the subscribe button
     */
    public void clickSubscribe() {
        clickElement(submitButton);
    }

    /**
     * Complete newsletter signup (enter email + click subscribe)
     * @param email Email address to subscribe with
     */
    public void subscribeWithEmail(String email) {
        enterEmail(email);
        clickSubscribe();
    }

    /**
     * Check if invalid email message is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isInvalidEmailMessageDisplayed() {
        return isElementDisplayed(invalidEmailMessage);
    }

    /**
     * Check if success message container is displayed
     * @return true if displayed, false otherwise
     */
    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(confirmedMessageContainer);
    }

    /**
     * Get the success message heading text
     * @return Success message heading text
     */
    public String getSuccessMessageHeading() {
        return getElementText(successMessageHeading);
    }

    /**
     * Get the email address shown in the success message
     * @return Email address from success message
     */
    public String getConfirmedEmail() {
        return getElementText(userEmailDisplay);
    }

    /**
     * Click the dismiss button on success message
     */
    public void clickDismissButton() {
        clickElement(dismissButton);
    }

    /**
     * Get the page title
     * @return Page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
}