package com.browserstack;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SelfHealLoginTest extends AppiumTest {

    /**
     * Login test WITH self-heal mode toggle in the app.
     * Toggles self-heal mode which causes DOM shift (IDs change to *_modified).
     * Uses SAME selectors as LoginTest.java (normal IDs without _modified suffix).
     *
     * Result:
     * - WITHOUT BrowserStack self-healing (selfHeal: false): FAILS (selectors don't match shifted DOM)
     * - WITH BrowserStack self-healing (selfHeal: true): PASSES (agent heals by finding correct elements)
     */

    @Test
    public void shouldLoginWithSelfHealModeEnabled() throws InterruptedException {
        // STEP 1: Toggle self-heal mode ON to cause DOM shift
        WebElement demoModeSwitch = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.example.self_healdemoapplication:id/demo_mode_switch")
            ));
        demoModeSwitch.click();

        // Wait for IDs to change
        Thread.sleep(1000);

        // STEP 2: Use SAME NORMAL locators as LoginTest (without _modified suffix)
        // After toggle, actual IDs are: user_dropdown_modified, sign_in_button_modified
        // But we intentionally use the OLD IDs to demonstrate self-healing

        // Click dropdown (using NORMAL ID - actual element is user_dropdown_modified)
        // Without self-healing: FAILS (selector doesn't match)
        // With self-healing: PASSES (agent finds user_dropdown_modified)
        WebElement userDropdown = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(
                AppiumBy.id("com.example.self_healdemoapplication:id/user_dropdown")
            ));
        userDropdown.click();

        // Select demo1 user by text (text remains the same)
        WebElement demo1User = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.elementToBeClickable(
                AppiumBy.androidUIAutomator("new UiSelector().text(\"demo1@example.com\")")
            ));
        demo1User.click();

        // Click Sign In button (using NORMAL ID - actual element is sign_in_button_modified)
        // Without self-healing: FAILS (selector doesn't match)
        // With self-healing: PASSES (agent finds sign_in_button_modified)
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
        Assert.assertNotNull(navigationBar, "Should navigate to home screen - login successful via self-healing");
    }
}
