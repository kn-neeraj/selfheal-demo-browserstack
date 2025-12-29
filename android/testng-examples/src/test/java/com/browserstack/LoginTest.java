package com.browserstack;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class LoginTest extends AppiumTest {

    /**
     * Baseline login test WITHOUT toggling self-heal mode in the app.
     * Uses normal IDs (user_dropdown, sign_in_button, navigation_bar).
     * This test ALWAYS PASSES and establishes the success baseline for the self-healing agent.
     */

    @Test
    public void shouldLoginWithValidCredentials() throws InterruptedException {
        // Click dropdown to open (using full resource ID)
        WebElement userDropdown = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.example.self_healdemoapplication:id/user_dropdown")
            ));
        userDropdown.click();

        // Select demo1 user by text
        WebElement demo1User = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"demo1@example.com\")")
            ));
        demo1User.click();

        // Click Sign In button (using full resource ID)
        WebElement signInButton = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.example.self_healdemoapplication:id/sign_in_button")
            ));
        signInButton.click();

        // Wait for navigation
        Thread.sleep(2000);

        // Assert navigation bar is visible (login successful)
        WebElement navigationBar = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.id("com.example.self_healdemoapplication:id/navigation_bar")
            ));
        Assert.assertNotNull(navigationBar, "Should navigate to home screen - login successful");
    }
}
