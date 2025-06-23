package com.browserstack;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

/**
 * This test suite demonstrates the self-healing feature of BrowserStack.
 * If the app UI changes and locators break, self-heal will attempt to recover
 * and find the correct elements, allowing tests to pass even with locator changes.
 * These tests are intentionally similar to BaseAppTest to show the difference
 * in behavior when self-heal is enabled.
 */
public class SelfHealAppTest extends AppiumTest {
    /**
     * This test suite demonstrates BrowserStack's self-healing feature.
     * Test names are updated to clearly describe the scenario and intent.
     */

    @Test
    public void shouldDisplayDemoScreenElements() {
        // Verifies main demo screen elements are present and visible
        WebElement demoTab = driver.findElement(AppiumBy.id("navigation_demo"));
        demoTab.click();
        WebElement demoTitle = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//android.widget.TextView[@resource-id='com.example.selfhealingtestapp:id/demo_text_title' and @text='Demonstration App']")));
        Assert.assertNotNull(demoTitle);
        Assert.assertNotNull(driver.findElement(AppiumBy.xpath(
            "//android.widget.TextView[@text='About This Demo App']")));
    }

    @Test
    public void shouldSelfHealOnProductCardXpathChange() {
        // Demonstrates self-heal for product card XPath change
        WebElement productsTab = driver.findElement(AppiumBy.id("navigation_products"));
        productsTab.click();
        WebElement firstProductCard = new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(
                AppiumBy.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='com.example.selfhealingtestapp:id/recycler_products']/androidx.cardview.widget.CardView[1]/android.view.ViewGroup")));
        WebElement firstProductName = firstProductCard.findElement(AppiumBy.id("product_name"));
        Assert.assertNotNull(firstProductName);
        WebElement infoIcon = firstProductCard.findElement(AppiumBy.id("product_info_icon"));
        infoIcon.click();
        WebElement infoPanel = new WebDriverWait(driver, Duration.ofSeconds(5))
            .until(ExpectedConditions.visibilityOf(
                firstProductCard.findElement(AppiumBy.id("info_card_product_panel"))));
        Assert.assertNotNull(infoPanel);
        Assert.assertNotNull(firstProductCard.findElement(AppiumBy.id("info_card_product_id")));
        Assert.assertNotNull(firstProductCard.findElement(AppiumBy.id("info_card_product_class")));
        Assert.assertNotNull(firstProductCard.findElement(AppiumBy.id("info_card_product_xpath")));
        infoPanel.click();
    }

    @Test
    public void shouldSelfHealOnCheckboxIdChange() {
        // Demonstrates self-heal for checkbox ID change
        WebElement interactiveTab = driver.findElement(AppiumBy.id("navigation_interactive"));
        interactiveTab.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("demo_button_primary_action")));
        WebElement featureBtn = driver.findElement(AppiumBy.id("demo_switch_example"));
        featureBtn.click();
        Assert.assertEquals(featureBtn.getAttribute("checked"), "true");
        WebElement acceptTermBtn = driver.findElement(AppiumBy.id("demo_checkbox_example"));
        acceptTermBtn.click();
        Assert.assertEquals(acceptTermBtn.getAttribute("checked"), "true");
        acceptTermBtn.click();
        Assert.assertEquals(acceptTermBtn.getAttribute("checked"), "false");
    }

    @Test
    public void shouldSelfHealOnSwitchInfoIconContentDescChange() {
        // Demonstrates self-heal for info icon content-desc change
        WebElement interactiveTab = driver.findElement(AppiumBy.id("navigation_interactive"));
        interactiveTab.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("demo_button_primary_action")));
        WebElement infoIconSwitch = driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc='Switch Info Icon']"));
        infoIconSwitch.click();
    }

    @Test
    public void shouldSelfHealOnSecondaryActionTextChange() throws Exception {
        // Demonstrates self-heal for secondary action text change
        WebElement interactiveTab = driver.findElement(AppiumBy.id("navigation_interactive"));
        interactiveTab.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("demo_button_primary_action")));
        WebElement secondaryActionBtn = driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"Secondary Action\")"));
        secondaryActionBtn.click();
        WebElement infoIconSecondary = driver.findElement(AppiumBy.id("info_icon_secondary"));
        Thread.sleep(2000); // Wait for info panel to appear
        infoIconSecondary.click();
    }

    @Test
    public void shouldNavigateBetweenTabsAndVerifyHeadings() {
        // Navigates between tabs and checks headings
        driver.findElement(AppiumBy.id("navigation_demo")).click();
        Assert.assertEquals(driver.findElement(AppiumBy.id("demo_text_title")).getText(), "Demonstration App");
        driver.findElement(AppiumBy.id("navigation_interactive")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("label_interactive_elements")));
        Assert.assertEquals(driver.findElement(AppiumBy.id("label_interactive_elements")).getText(), "Interactive Elements");
        driver.findElement(AppiumBy.id("navigation_products")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("product_list_heading")));
        Assert.assertNotNull(driver.findElement(AppiumBy.id("product_list_heading")));
        driver.findElement(AppiumBy.id("navigation_demo")).click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("navigation_demo")));
        Assert.assertEquals(driver.findElement(AppiumBy.id("demo_text_title")).getText(), "Demonstration App");
    }
}
